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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.javaLab.service.interfaces.UserService;
import ru.kpfu.itis.javaLab.web.forms.RegistrationForm;

import javax.validation.Valid;

/**
 * Created by Safin Ramil on 07.06.17
 * RamilSafNab1996@gmail.com
 *
 * Authentication and authorization controller
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
    public ModelAndView showLogin(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout
    ) {

        ModelAndView modelAndView = new ModelAndView("login");

        if (error != null) {
            modelAndView.addObject("error", "Invalid email or password!");
        } else if (logout != null) {
            modelAndView.addObject("logout", "You've been logged out successfully.");
        }

        return modelAndView;
    }


    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {

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

        logger.info("Form hasn't got any errors");

        userService.register(form).ifPresent(user -> {

            logger.info("User's been registered");

            redirectAttributes.addFlashAttribute("message",
                String.format("You're welcome, %s!", user.getEmail()));
        });

        logger.info("redirect ot login");

        return "redirect:/login";
    }

}
