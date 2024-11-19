package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            // Attempt to register the user
            userService.registerUser(user);

            // If registration is successful, add a success message
            model.addAttribute("successMessage", "Registration successful! Please log in.");
            return "redirect:/login";  // Redirect to the login page after successful registration
        } catch (IllegalArgumentException e) {
            // If user already exists, handle the exception and add an error message
            model.addAttribute("errorMessage", e.getMessage());
            return "register";  // Return to the registration page with an error message
        }
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String showMainWindow(Model model) {
        return "main";
    }
}