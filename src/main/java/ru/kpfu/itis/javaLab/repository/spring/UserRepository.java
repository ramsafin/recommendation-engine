package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javaLab.model.entities.User;

import java.util.Optional;

/**
 * Created by Safin Ramil on 07.06.17
 * RamilSafNab1996@gmail.com
 * <p>
 * User entity Spring Data repository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by login
     * Select user with joining role
     *
     * @param email - user email
     * @return found user or null
     */

    @EntityGraph(value = "graphs.User.roles")
    Optional<User> findByEmailIgnoreCase(@Param("email") String email);


    /**
     * Check whether user exists or not
     *
     * @param email - user email
     * @return true - user with specified login exists
     */

    boolean existsByEmailIgnoreCase(@Param("email") String email);

}
