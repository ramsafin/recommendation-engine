package ru.kpfu.itis.javaLab.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.javaLab.model.ajax.ErrorResponseBody;
import ru.kpfu.itis.javaLab.model.entities.Post;
import ru.kpfu.itis.javaLab.model.entities.User;
import ru.kpfu.itis.javaLab.security.CustomUserDetails;
import ru.kpfu.itis.javaLab.service.interfaces.BlogService;
import ru.kpfu.itis.javaLab.web.exceptions.PageNotFoundException;
import ru.kpfu.itis.javaLab.web.forms.CommentForm;
import ru.kpfu.itis.javaLab.web.utils.Pager;

import javax.validation.Valid;
import java.util.stream.Collectors;

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
    public ModelAndView showBlog(@RequestParam(defaultValue = "1") Integer page, Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView("blog");

        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            modelAndView.addObject("user", userDetails.getUser());
        }

        // find page
        Integer evalPage = page < 1 ? 0 : page - 1;
        Page<Post> postsPage = blogService.getPostsByPage(new PageRequest(evalPage, PAGE_SIZE, Sort.Direction.DESC,
            "updated"));

        // posts
        modelAndView.addObject("posts", postsPage.getContent());
        modelAndView.addObject("tags", blogService.getAllTags());

        // pagination
        modelAndView.addObject("pager", new Pager(postsPage.getTotalPages(), postsPage.getNumber(), PAGE_SIZE));
        modelAndView.addObject("pageSize", PAGE_SIZE);
        modelAndView.addObject("currentPage", postsPage.getNumber() + 1);
        modelAndView.addObject("pages", postsPage.getTotalPages());

        return modelAndView;
    }


    @GetMapping("/blog/post/{id}")
    public ModelAndView showBlogPost(@PathVariable("id") Long id, Authentication authentication) {

        ModelAndView modelAndView = new ModelAndView("post");

        Post post = blogService.getPostById(id).orElseThrow(PageNotFoundException::new);

        modelAndView.addObject("post", post);

        modelAndView.addObject("tags", blogService.getAllTags());

        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            modelAndView.addObject("user", userDetails.getUser());
        }

        return modelAndView;
    }


    @ResponseBody
    @PostMapping(value = "/blog/post/comment")
    public ResponseEntity<?> commentPost(
        @Valid @RequestBody CommentForm form,
        Errors errors, Authentication authentication
    ) {

        if (errors.hasErrors()) {
            String message = errors.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
            return ResponseEntity.badRequest().body(new ErrorResponseBody(message));
        }

        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        return ResponseEntity.ok(blogService.saveComment(user, form));
    }
}
