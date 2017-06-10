package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.javaLab.model.entities.Star;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 * <p>
 * Star entity repository
 */

public interface StarRepository extends JpaRepository<Star, Long> {

    /**
     * Check if star exists
     *
     * @param userId - user id
     * @param postId - post id
     * @return true - if given user mark a star to the post
     */

    boolean existsByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);


}
