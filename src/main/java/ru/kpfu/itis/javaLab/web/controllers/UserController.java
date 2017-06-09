package ru.kpfu.itis.javaLab.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Safin Ramil on 07.06.17
 * Safin.Ramil@ordotrans.ru
 */

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/user")
    public Principal user(Principal principal) {

        return principal;
    }
}