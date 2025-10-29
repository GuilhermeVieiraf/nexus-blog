package com.nexus.nexus_blog.controller;

import com.nexus.nexus_blog.dto.PostRequestDto;
import com.nexus.nexus_blog.dto.PostResponseDto;
import com.nexus.nexus_blog.model.Post;
import com.nexus.nexus_blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

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
            postService.createPost(postRequestDto, principal);
        } catch (UsernameNotFoundException e) {
            return "post/create-post";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable UUID id, Model model) {
        try {
            PostResponseDto postDto = postService.getPostById(id);
            model.addAttribute("post", postDto);
            return "posts/view-post";
        } catch (RuntimeException e) {
            return "redirect:/";
        }
    }

}
