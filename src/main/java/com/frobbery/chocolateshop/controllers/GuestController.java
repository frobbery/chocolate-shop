package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.services.ChocolateService;
import com.frobbery.chocolateshop.services.CookingService;
import com.frobbery.chocolateshop.services.OrderService;
import com.frobbery.chocolateshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class GuestController {
    private final UserService userService;
    private final OrderService orderService;
    private final CookingService cookingService;
    private final ChocolateService chocolateService;

    public GuestController(UserService userService, OrderService orderService, CookingService cookingService, ChocolateService chocolateService) {
        this.userService = userService;
        this.orderService = orderService;
        this.cookingService = cookingService;
        this.chocolateService = chocolateService;
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
                    Map<Long, List<String>> paidOrdersBasket = orderService.getPaidOrdersBasket();
                    model.addAttribute("paidOrdersBasket", paidOrdersBasket);
                    return "worker";
                case "ChefRole":
                    Map<String,Integer> cookingOrders = cookingService.getAllCookingOrders();
                    model.addAttribute("cooking orders", cookingOrders);
                    return "chef";
                default:
                    List<String> chocolateNames = chocolateService.getAllChocolatesNames();
                    model.addAttribute("chocolateNames", chocolateNames);
                    model.addAttribute("user_id", result.substring(0, result.length() - 4));
                    return "catalogue";
            }
        }
    }

    @GetMapping("/register")
    public String getRegister() {
        return "reg";
    }

    @PostMapping("/register")
    public String postRegister(String email, String password, String phone, Model model) {
        try {
            Long.valueOf(phone);
        }
        catch (NumberFormatException e) {
            model.addAttribute("message", "The phone number is in an invalid format");
            return "reg";
        }
        String result = userService.register(email, password, Long.valueOf(phone));
        if (result != null) {
            model.addAttribute("message", result);
            return "reg";
        }
        else {
            model.addAttribute("message", "The registration went successfully.");
            return "auth";
        }
    }
}
