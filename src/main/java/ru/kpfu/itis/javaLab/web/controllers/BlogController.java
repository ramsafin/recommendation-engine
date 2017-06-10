package ru.kpfu.itis.javaLab.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 */

@Controller
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping("/blog")
    public ModelAndView showBlog() {

        logger.debug("/blog request");

        ModelAndView modelAndView = new ModelAndView("blog");

        modelAndView.addObject("test", "Test phrase");

        return modelAndView;
    }
}
