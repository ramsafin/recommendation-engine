package ru.kpfu.itis.javaLab.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.javaLab.model.ajax.CommentResponseBody;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.model.entities.Tag;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.web.forms.CommentForm;

import java.util.List;
import java.util.Optional;

/**
 * Created by Safin Ramil on 11.06.17
 * RamilSafNab1996@gmail.com
 */

@Service
public interface BlogService {

    /**
     * Get posts page
     *
     * @param pageable - page and page size structure
     * @return posts page (can be empty)
     */

    Page<Post> getPostsByPage(Pageable pageable);

    /**
     * Get all tags
     *
     * @return all tags
     */

    List<Tag> getAllTags();


    /**
     * Get post bi specified id
     *
     * @param id - post's id
     * @return post or null
     */

    Optional<Post> getPostById(Long id);


    /**
     * Save post comment
     *
     * @param commenter - user who's written comment
     * @param form      - comment form
     * @return saved comment response body
     */

    CommentResponseBody saveComment(User commenter, CommentForm form);


    /**
     * Rate the post with specified id and user
     *
     * @param postId - post to rate
     * @param user   - user
     * @return true if saved, false if user's already rated this post
     */

    boolean ratePost(Long postId, User user);
}
