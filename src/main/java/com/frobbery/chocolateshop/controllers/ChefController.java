package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.services.CookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ChefController {
    private final CookingService cookingService;

    public ChefController(CookingService cookingService) {
        this.cookingService = cookingService;
    }

    @GetMapping("/chef")
    public String getChef(Model model) {
        Map<List<String>,Integer> cookingOrders = cookingService.getAllCookingOrders();
        model.addAttribute("cooking_orders", cookingOrders);
        return "chef";
    }

    @GetMapping("/chef/taste-update")
    public String getTasteUpdate() {
        return "taste-update";
    }

    @PostMapping("chef/taste-update")
    public String postCookingUpdate(String cookingId, Integer quantity, Model model){
        try {
            Long.valueOf(cookingId);
        }
        catch (NumberFormatException e) {
            model.addAttribute("message", "The id is in an invalid format");
            return "taste-update";
        }
        String result = cookingService.updateCookingAndChocolate(Long.valueOf(cookingId), quantity);
        if (result != null) {
            model.addAttribute("message", result);
            return "taste-update";
        }
        else {
            model.addAttribute("message", "Update went successfully");
            return "chef";
        }
    }
}
