package ru.kpfu.itis.javaLab.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.service.interfaces.BlogService;

/**
 * Created by Safin Ramil on 10.06.17
 * RamilSafNab1996@gmail.com
 */

@Controller
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    private static final int PAGE_SIZE = 5;

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @GetMapping("/blog")
    public ModelAndView showBlog(@RequestParam(defaultValue = "1") Integer page) {

        logger.debug("/blog request");

        ModelAndView modelAndView = new ModelAndView("blog");

        // find page
        Integer evalPage = page < 1 ? 0 : page - 1;

        Page<Post> postsPage = blogService.getPostsByPage(new PageRequest(evalPage, PAGE_SIZE));

        logger.warn("Content: " + postsPage.getContent().toString());
        logger.warn("Number: " + postsPage.getNumber());
        logger.warn("Number of elements: " + postsPage.getNumberOfElements());
        logger.warn("Size: " + postsPage.getSize());
        logger.warn("Total elements: " + postsPage.getTotalElements());
        logger.warn("Total pages: " + postsPage.getTotalPages());

        // posts
        modelAndView.addObject("posts", postsPage.getContent());


        // pagination
        modelAndView.addObject("pageSize", PAGE_SIZE);
        modelAndView.addObject("currentPage", postsPage.getNumber() + 1);
        modelAndView.addObject("pages", postsPage.getTotalPages());

        return modelAndView;
    }


    @GetMapping("/blog/post/{id}")
    public ModelAndView showBlogPost(@PathVariable("id") Long id) {

        ModelAndView modelAndView = new ModelAndView("post");

        modelAndView.addObject("test", "Test phrase");

        return modelAndView;
    }
}
