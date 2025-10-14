package com.nexus.nexus_blog.controller;

import com.nexus.nexus_blog.model.User;
import com.nexus.nexus_blog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") User user,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

}
