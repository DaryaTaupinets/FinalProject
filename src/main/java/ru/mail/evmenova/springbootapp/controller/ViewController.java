package ru.mail.evmenova.springbootapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mail.evmenova.springbootapp.model.User;
import ru.mail.evmenova.springbootapp.service.UserService;

@Controller
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String main(Model model, Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        model.addAttribute("currentUser", currentUser);

        return currentUser.isAdmin() ? "admin-page" : "user-page";
    }
}
