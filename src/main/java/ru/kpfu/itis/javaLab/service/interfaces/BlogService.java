package ru.kpfu.itis.javaLab.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.model.entities.Tag;

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

}
