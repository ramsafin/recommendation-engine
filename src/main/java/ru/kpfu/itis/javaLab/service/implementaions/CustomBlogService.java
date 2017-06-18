package ru.kpfu.itis.javaLab.service.implementaions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.javaLab.model.response.CommentResponseBody;
import ru.kpfu.itis.javaLab.model.entities.*;
import ru.kpfu.itis.javaLab.repository.spring.CommentRepository;
import ru.kpfu.itis.javaLab.repository.spring.PostRepository;
import ru.kpfu.itis.javaLab.repository.spring.StarRepository;
import ru.kpfu.itis.javaLab.repository.spring.TagRepository;
import ru.kpfu.itis.javaLab.repository.spring.neo4j.Neo4jPostRepository;
import ru.kpfu.itis.javaLab.service.interfaces.BlogService;
import ru.kpfu.itis.javaLab.web.forms.CommentForm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Safin Ramil on 11.06.17
 * RamilSafNab1996@gmail.com
 */

@Service
public class CustomBlogService implements BlogService {

    private static final Logger logger = LoggerFactory.getLogger(CustomBlogService.class);

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final StarRepository starRepository;
    private final Neo4jPostRepository neo4jPostRepository;

    @Autowired
    public CustomBlogService(
        PostRepository postRepository, TagRepository tagRepository,
        CommentRepository commentRepository, StarRepository starRepository,
        Neo4jPostRepository neo4jPostRepository
    ) {
        this.tagRepository = tagRepository;
        this.starRepository = starRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.neo4jPostRepository = neo4jPostRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> getPostsByPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    @Transactional
    public CommentResponseBody saveComment(User commenter, CommentForm form) {

        Comment comment = commentRepository.save(fromCommentFormAndUser(form, commenter));

        return new CommentResponseBody(comment.getCommenterName(), comment.getCreated(), comment.getContent());
    }


    private Comment fromCommentFormAndUser(CommentForm form, User commenter) {
        Comment comment = new Comment();
        comment.setCommenterId(commenter.getId());
        comment.setCommenterName(commenter.getEmail());
        comment.setContent(form.getContent());
        comment.setCreated(LocalDateTime.now());
        comment.setPostId(form.getPostId());
        return comment;
    }

    @Override
    @Transactional
    public boolean ratePost(Long postId, User user) {

        // if exist return false
        if (!starRepository.existsByUserIdAndPostId(user.getId(), postId)) {
            return starRepository.save(formStar(postId, user.getId())) != null;
        }

        return false;
    }

    private Star formStar(Long postId, Long userId) {
        Star star = new Star();
        star.setCreated(LocalDateTime.now());
        star.setPostId(postId);
        star.setUserId(userId);
        return star;
    }

    @Override
    @Transactional
    public List<Post> getRecentPosts(int postsNumber) {

        return getPostsByPage(new PageRequest(0, postsNumber, Sort.Direction.DESC, "updated"))
            .getContent();
    }

    @Override
    @Transactional
    public List<Post> getRecommendedPosts(User user) {

        List<Long> postIds = neo4jPostRepository.findRecommended(user.getId());

        return postRepository.findByIdsIn(postIds);
    }

}
