package ru.kpfu.itis.javaLab.service.implementaions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.model.entities.Tag;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.repository.spring.PostRepository;
import ru.kpfu.itis.javaLab.repository.spring.TagRepository;
import ru.kpfu.itis.javaLab.service.interfaces.AdminService;
import ru.kpfu.itis.javaLab.service.interfaces.StorageService;
import ru.kpfu.itis.javaLab.web.forms.PostForm;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */

@Service
public class CustomAdminService implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(CustomAdminService.class);

    private final PostRepository postRepository;

    private final StorageService storageService;

    private final TagRepository tagRepository;

    @Autowired
    public CustomAdminService(
        PostRepository postRepository, StorageService storageService,
        TagRepository tagRepository
    ) {
        this.postRepository = postRepository;
        this.storageService = storageService;
        this.tagRepository = tagRepository;
    }

    @Override
    public Post addPost(PostForm postForm, User author) {
        return postRepository.save(createPost(postForm, author));
    }

    private Post createPost(PostForm form, User author) {
        Post post = new Post();
        post.setAuthorId(author.getId());
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        LocalDateTime now = LocalDateTime.now();

        post.setCreated(now);
        post.setUpdated(now);

        String savePicture = storageService.store(form.getPicture());

        post.setPicture(savePicture);

        if (!form.getTags().trim().isEmpty()) {
            post.getTags()
                .addAll(Arrays.stream(form.getTags().split(","))
                    .map(String::trim)
                    .filter(tag -> !tag.isEmpty())
                    .map(tag -> tagRepository.findByName(tag).orElse(new Tag(tag)))
                    .collect(Collectors.toList()));
        }

        return post;
    }
}
