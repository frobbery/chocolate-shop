package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.services.ChocolateService;
import com.frobbery.chocolateshop.services.CookingService;
import com.frobbery.chocolateshop.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class WorkerController {
    private final OrderService orderService;
    private final ChocolateService chocolateService;
    private final CookingService cookingService;

    public WorkerController(OrderService orderService, ChocolateService chocolateService, CookingService cookingService) {
        this.orderService = orderService;
        this.chocolateService = chocolateService;
        this.cookingService = cookingService;
    }

    @GetMapping("/worker")
    public String getWorker(Model model) {
        Map<Long, List<String>> paidOrdersBasket = orderService.getPaidOrdersBasket();
        model.addAttribute("paidOrdersBasket", paidOrdersBasket);
        return "worker";
    }

    @GetMapping("/worker/warehouse")
    public String getWarehouse(Model model) {
        Map<String,Integer> warehouse = chocolateService.getChocolateWithQuantity();
        model.addAttribute("chocolates", warehouse);
        return "warehouse";
    }

    @GetMapping("/worker/warehouse-update")
    public String getWarehouseUpdate(Model model) {
        model.addAttribute("chocolatesNames", chocolateService.getAllChocolatesNames());
        return "warup";
    }

    @PostMapping("/worker/warehouse-update")
    public String postWarehouseUpdate(String chocolatesNamesInOneString,Model model) {
        String[] words = chocolatesNamesInOneString.split(",");
        List<String> chocolatesNames = new ArrayList<>();
        Collections.addAll(chocolatesNames, words);
        String result = chocolateService.getIntoBox(chocolatesNames);
        if (result != null) {
            model.addAttribute("message", result);
            return "warup";
        }
        else {
            model.addAttribute("message", "Update went successfully");
            return "worker";
        }
    }

    @GetMapping("worker/cooking-update")
    public String getCookingUpdate() {
        return "cookup";
    }

    @PostMapping("worker/cooking-update")
    public String postCookingUpdate(String taste, String quantity, Model model){
        String result = cookingService.addCookingOrder(taste, Integer.valueOf(quantity));
        if (result != null) {
            model.addAttribute("message", result);
            return "cookup";
        }
        else {
            model.addAttribute("message", "Update went successfully");
            return "worker";
        }
    }

    @GetMapping("worker/status-update")
    public String getStatusWorkerUpdate() {
        return "worstatup";
    }

    @PostMapping("worker/status-update")
    public String postStatusWorkerUpdate(String id, Model model) {
        try {
            Long.valueOf(id);
        }
        catch (NumberFormatException e) {
            model.addAttribute("message", "The id is in an invalid format");
            return "worstatup";
        }
        String result = orderService.updateOrderStatusToGathered(Long.valueOf(id));
        if (result != null) {
            model.addAttribute("message", result);
            return "worstatup";
        }
        else {
            model.addAttribute("message", "Update went successfully");
            return "worker";
        }
    }
}
