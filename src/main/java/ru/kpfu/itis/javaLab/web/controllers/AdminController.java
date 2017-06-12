package ru.kpfu.itis.javaLab.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.security.CustomUserDetails;
import ru.kpfu.itis.javaLab.service.interfaces.AdminService;
import ru.kpfu.itis.javaLab.web.forms.PostForm;

import javax.validation.Valid;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */

@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/blog")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView showAdminPage(Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView("admin");

        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        modelAndView.addObject("admin", user);

        modelAndView.addObject("post", new PostForm());

        return modelAndView;
    }


    @PostMapping("/admin/blog/add_post")
    @PreAuthorize("hasRole('ADMIN')")
    public String addPost(
        @Valid @ModelAttribute("post") PostForm form,
        BindingResult bindingResult, Authentication authentication,
        RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            return "admin";
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        adminService.addPost(form, userDetails.getUser());

        redirectAttributes.addFlashAttribute("message", "Post's been successfully added!");

        return "redirect:/admin/blog";
    }

}
