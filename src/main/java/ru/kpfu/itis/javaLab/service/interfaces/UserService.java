package ru.kpfu.itis.javaLab.service.interfaces;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.web.forms.RegistrationForm;

import java.util.Optional;

/**
 * Created by Safin Ramil on 08.06.17
 * Safin.Ramil@ordotrans.ru
 */
@Service
public interface UserService {

    /**
     * Find user by email address
     * @param email - user email address
     * @return user or null
     */
    Optional<User> findByEmail(String email);

    /**
     * Save new created user
     * @param form - user form object
     * @return created user
     */
    Optional<User> register(RegistrationForm form);
}
