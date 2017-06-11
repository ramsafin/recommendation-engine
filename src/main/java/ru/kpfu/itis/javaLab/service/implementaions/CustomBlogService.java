package ru.kpfu.itis.javaLab.service.implementaions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.repository.spring.PostRepository;
import ru.kpfu.itis.javaLab.service.interfaces.BlogService;

/**
 * Created by Safin Ramil on 11.06.17
 * RamilSafNab1996@gmail.com
 */

@Service
public class CustomBlogService implements BlogService {

    private static final Logger logger = LoggerFactory.getLogger(CustomBlogService.class);

    private final PostRepository postRepository;

    @Autowired
    public CustomBlogService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> getPostsByPage(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

}
