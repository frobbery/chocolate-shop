package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController {
    private final UserService userService;

    public GuestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authorize")
    public String getAuthorize() {
        return "auth";
    }

    @PostMapping("/authorize")
    public String postAuthorize(String phoneNumber, String password, Model model) {
        try {
            Long.valueOf(phoneNumber);
        }
        catch (NumberFormatException e) {
            model.addAttribute("message", "The phone number is in an invalid format");
            return "auth";
        }
        String result = userService.authorize(Long.valueOf(phoneNumber), password);
        if (!result.endsWith("Role")) {
            model.addAttribute("message", result);
            return "auth";
        }
        else {
            switch (result) {
                case "AdminRole":
                    return "admin";
                case "WorkerRole":
                    return "worker";
                case "ChefRole":
                    return "chef";
                default:
                    return "user";
            }
        }
    }
}
