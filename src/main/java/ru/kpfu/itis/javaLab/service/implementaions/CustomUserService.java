package ru.kpfu.itis.javaLab.service.implementaions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.javaLab.model.entities.Role;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.model.enums.UserRole;
import ru.kpfu.itis.javaLab.repository.spring.RoleRepository;
import ru.kpfu.itis.javaLab.repository.spring.UserRepository;
import ru.kpfu.itis.javaLab.service.interfaces.UserService;
import ru.kpfu.itis.javaLab.web.forms.RegistrationForm;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Safin Ramil on 08.06.17
 * Safin.Ramil@ordotrans.ru
 */

@Service
public class CustomUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserService(
        UserRepository userRepository, RoleRepository roleRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ru.kpfu.itis.javaLab.model.entities.User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    @Transactional
    public Optional<User> register(RegistrationForm form) {

        // check if exists
        if (userRepository.existsByEmail(form.getEmail())) {

            logger.warn(String.format("User with email '%s' already exists", form.getEmail()));

            return Optional.empty(); // user exists
        }

        logger.info("Saving user ...");

        return Optional.of(userRepository.save(buildFromDto(form)));
    }


    private User buildFromDto(RegistrationForm form) {
        User user = new User(form.getEmail(), passwordEncoder.encode(form.getPassword()));
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        user.setRegistered(LocalDateTime.now());
        user.addRole(roleRepository.findByName(UserRole.ROLE_USER)
            .orElse(new Role(UserRole.ROLE_USER)));
        return user;
    }
}
