package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.services.ChocolateService;
import com.frobbery.chocolateshop.services.OrderService;
import com.frobbery.chocolateshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

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
    public String getCatalogue(String user_id, Model model) {
        List<String> chocolateNames = chocolateService.getAllChocolatesNames();
        model.addAttribute("chocolateNames", chocolateNames);
        model.addAttribute("user_id", user_id);
        return "catalogue";
    }

    @PostMapping("/catalogue")
    public String postCatalogue(String user_id, String selectedChocolates, Model model) {
        model.addAttribute("user_id", user_id);
        List<String> chocolateNames = chocolateService.getAllChocolatesNames();
        model.addAttribute("chocolateNames", chocolateNames);
        String[] words = selectedChocolates.split(",");
        List<String> selectedChoco = new ArrayList<>();
        Collections.addAll(selectedChoco, words);
        String result = userService.setBasketFromHand(Long.valueOf(user_id), selectedChoco);
        model.addAttribute("message", Objects.requireNonNullElse(result, "Selected tastes are now in basket"));
        return "catalogue";
    }

    @GetMapping("/test")
    public String getTest(String user_id, Model model) {
        model.addAttribute("user_id", user_id);
        return "test";
    }

    @PostMapping("test")
    public String postTest(String user_id, String testSelect, Model model) {
        List<String> chocolateNames = chocolateService.getAllChocolatesNames();
        model.addAttribute("chocolateNames", chocolateNames);
        model.addAttribute("user_id", user_id);
        boolean testResult;
        testResult = testSelect.equals("milk");
        String result = userService.setBasketFromTest(Long.valueOf(user_id), testResult);
        model.addAttribute("message", Objects.requireNonNullElse(result, "Your box is now in the basket"));
        return "catalogue";
    }

    @GetMapping("/basket")
    public String getBasket(String user_id, Model model) {
        model.addAttribute("user_id", user_id);
        List<String> basket = userService.getBasketOfUser(Long.valueOf(user_id));
        model.addAttribute("basket", basket);
        return "basket";
    }

    @PostMapping("/basket")
    public String cleanBasket(String user_id, Model model) {
        model.addAttribute("user_id", user_id);
        String result = userService.cleanBasket(Long.valueOf(user_id));
        if (result != null) {
            List<String> basket = userService.getBasketOfUser(Long.valueOf(user_id));
            model.addAttribute("basket", basket);
            model.addAttribute("message", result);
            return "basket";
        }
        else {
            model.addAttribute("message", "Basket is now empty");
            List<String> chocolateNames = chocolateService.getAllChocolatesNames();
            model.addAttribute("chocolateNames", chocolateNames);
            return "catalogue";
        }
    }

    @GetMapping("/checkout")
    public String getCheckout (String user_id, Model model) {
        model.addAttribute("user_id", user_id);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String postCheckout(String user_id, String address, Model model) {
        List<String> chocolateNames = chocolateService.getAllChocolatesNames();
        model.addAttribute("chocolateNames", chocolateNames);
        model.addAttribute("user_id", user_id);
        String result = userService.placeOrder(Long.valueOf(user_id), address);
        model.addAttribute("message", Objects.requireNonNullElse(result, "Order has been placed"));
        return "catalogue";
    }

    @GetMapping("/cabinet")
    public String getCabinet(String user_id, Model model) {
        model.addAttribute("user_id", user_id);
        Map<Long,String> ordersWithStatus = orderService.getOrdersOfUser(Long.valueOf(user_id));
        model.addAttribute("ordersWithStatus", ordersWithStatus);
        return "cabinet";
    }
}
