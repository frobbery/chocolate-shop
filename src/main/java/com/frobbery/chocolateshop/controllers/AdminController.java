package com.frobbery.chocolateshop.controllers;

import com.frobbery.chocolateshop.entities.Status;
import com.frobbery.chocolateshop.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;
import java.util.Map;

@Controller
public class AdminController {
    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/admin/registered-orders")
    public String getRegisteredOrders(Model model){
        Map<Long,Long> registeredOrders = orderService.getRegisteredOrdersWithPhone();
        model.addAttribute("registeredOrders", registeredOrders);
        return "registered";
    }

    @GetMapping("/admin/gathered-orders")
    public String getGatheredOrders(Model model) {
        Map<Long,String> gatheredOrders = orderService.getGatheredOrdersWithAddress();
        model.addAttribute("gatheredOrders", gatheredOrders);
        return "gathered";
    }

    @GetMapping("/admin/status-update")
    public String getAdminStatusUpdate() {
        return "admin-status-update";
    }


    @PostMapping("/admin/status-update")
    public String postAdminStatusUpdate(String id, String statusSelected, Model model) {
        try {
            Long.valueOf(id);
        }
        catch (NumberFormatException e) {
            model.addAttribute("message", "The id is in an invalid format");
            return "admin-status-update";
        }
        Status status = Status.valueOf(statusSelected.toUpperCase(Locale.ROOT));
        String result;
        if (status.equals(Status.PAID)) {
            result = orderService.updateOrderStatusToPaid(Long.valueOf(id));
        }
        else {
            result = orderService.updateOrderStatusToSent(Long.valueOf(id));
        }
        if (result != null) {
            model.addAttribute("message", result);
            return "admin-status-update";
        }
        else {
            model.addAttribute("message", "Update went successfully");
            return "admin";
        }
    }
}
