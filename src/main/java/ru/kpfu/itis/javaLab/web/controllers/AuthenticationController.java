package ru.kpfu.itis.javaLab.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.javaLab.service.interfaces.UserService;
import ru.kpfu.itis.javaLab.web.forms.RegistrationForm;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Safin Ramil on 07.06.17
 * Safin.Ramil@ordotrans.ru
 */

@Controller
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String showLogin() {
        return "login";
    }


    @GetMapping("/signup")
    public String showRegistrationForm(Model model, Principal principal) {

        if (principal != null) return "redirect:/";

        model.addAttribute("userForm", new RegistrationForm());

        return "signup";
    }


    @PostMapping("/signup")
    public String processRegistration(
        @Valid @ModelAttribute("userForm") RegistrationForm form,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {

        logger.debug("register user");

        if (bindingResult.hasErrors()) {
            logger.error("Form has errors");
            return "signup";
        }

        logger.info("Form hasn't any error");

        userService.register(form).ifPresent(user -> {

            logger.info("User's been registered");

            logger.info("ID: " + user.getId());

            redirectAttributes.addFlashAttribute("success_registration_user", user);
        });

        return "redirect:/login";
    }

}
