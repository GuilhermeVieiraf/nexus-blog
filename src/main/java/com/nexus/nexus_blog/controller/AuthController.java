package com.nexus.nexus_blog.controller;

import com.nexus.nexus_blog.dto.PostResponseDto;
import com.nexus.nexus_blog.dto.UserRegistrationDto;
import com.nexus.nexus_blog.service.PostService;
import com.nexus.nexus_blog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto("", "", ""));
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        userService.createUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "Visitante");
        }
        List<PostResponseDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "home";
    }

}
