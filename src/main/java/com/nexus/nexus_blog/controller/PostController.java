package com.nexus.nexus_blog.controller;

import com.nexus.nexus_blog.dto.PostRequestDto;
import com.nexus.nexus_blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("postRequest", new PostRequestDto("", ""));
        return "posts/create-post";
    }

    @PostMapping
    public String processCreatePost(@Valid @ModelAttribute("postRequest") PostRequestDto postRequestDto,
                                    BindingResult bindingResult,
                                    Principal principal,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "posts/create-post";
        }

        try {
            postService.cretePost(postRequestDto, principal);
        } catch (UsernameNotFoundException e) {
            return "post/create-post";
        }
        return "redirect:/";
    }

}
