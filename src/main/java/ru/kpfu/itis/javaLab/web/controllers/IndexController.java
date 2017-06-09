package ru.kpfu.itis.javaLab.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.javaLab.service.interfaces.UserService;

import java.security.Principal;

/**
 * Created by Safin Ramil on 06.06.17
 * Safin.Ramil@ordotrans.ru
 */

@Controller
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showIndex(Model model, Principal principal) {

        if (principal != null) {

            System.out.println(principal.getName());

            userService.findByEmail(principal.getName())
                .ifPresent(user -> model.addAttribute("user", user));
        }


        return "index_new";
    }

    @GetMapping(value = "/test")
    public String show() {
        return "index";
    }

}
