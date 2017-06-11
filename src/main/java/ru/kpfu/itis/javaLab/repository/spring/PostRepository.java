package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javaLab.model.entities.Post;

import java.util.Optional;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 * <p>
 * Post entity repository
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Find post by specified id
     * Select post with joining tags
     *
     * @param id - post id
     * @return post or null
     */

    @EntityGraph(value = "graphs.Post.tags")
    Optional<Post> findById(@Param("id") Long id);


    /**
     * Find 10 posts sorted by updated datetime in desc order
     *
     * @return posts
     */

    @EntityGraph(value = "graphs.Post.tags")
    Page<Post> findByOrderByUpdatedDesc(Pageable pageable);

}
