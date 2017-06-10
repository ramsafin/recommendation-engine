package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javaLab.model.entities.Post;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 * <p>
 * Post entity repository
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


}
