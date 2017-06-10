package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javaLab.model.entities.Comment;

import java.util.List;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 * <p>
 * Comment entity repository
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Find all comments which are written by specified user
     *
     * @param commenterId - the user id
     * @return comments
     */

    List<Comment> findAllByCommenterIdOrderByCreatedDesc(@Param("commenterId") Long commenterId);


    /**
     * Find all comments which are written in specified post
     *
     * @param postId - post
     * @return comments
     */
    List<Comment> findAllByPostIdOrderByCreatedDesc(@Param("postId") Long postId);

}
