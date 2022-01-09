package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.services.ChocolateService;
import com.frobbery.chocolateshop.services.OrderService;
import com.frobbery.chocolateshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final ChocolateService chocolateService;

    public UserController(UserService userService, OrderService orderService, ChocolateService chocolateService) {
        this.userService = userService;
        this.orderService = orderService;
        this.chocolateService = chocolateService;
    }

    @GetMapping("/catalogue")
    public String getCatalogue(String userId, Model model) {
        List<String> chocolateNames = chocolateService.getAllChocolatesNames();
        model.addAttribute("chocolateNames", chocolateNames);
        model.addAttribute("user_id", userId);
        return "catalogue";
    }

    @PostMapping("/catalogue")
    public String postCatalogue(String interest, String user_id, Model model) {
        model.addAttribute("user_id", user_id);
        return "catalogue";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        return "test";
    }

    @PostMapping("test/{userId}")
    public String postTest(@PathVariable Long userId, String testSelect, Model model) {
        model.addAttribute("user_id", userId);
        return "test";
    }
}
