package ru.kpfu.itis.javaLab.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javaLab.model.entities.Role;
import ru.kpfu.itis.javaLab.model.enums.UserRole;

import java.util.Optional;

/**
 * Created by Safin Ramil on 07.06.17
 * RamilSafNab1996@gmail.com
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRole name);
}
