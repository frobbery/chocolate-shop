package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.Status;
import com.frobbery.chocolateshop.entities.User;
import com.frobbery.chocolateshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private BasketService basketService;

    public OrderService(BasketService basketService) {
        this.basketService = basketService;
    }

    public String createOrder(User user, String address) {
        if (user.getBasket() == null) {
            return "Корзина пуста";
        }
        else {
            orderRepository.save(new Order(user, address));
            return null;
        }
    }

    public Map<Long,Status> getOrdersOfUser(User user) {
        List<Order> ordersOfUser = orderRepository.getOrderByUser(user);
        Map<Long,Status> orders = new HashMap<>();
        for (Order order : ordersOfUser) {
            orders.put(order.getId(), order.getStatus());
        }
        return orders;
    }

    public Map<Long,List<String>> getPaidOrdersBasket() {
        Map<Long,List<String>> ordersBasket = new HashMap<>();
        List<Order> paidOrders = orderRepository.getOrderByStatus_Paid();
        for (Order order : paidOrders) {
            List<String> chocolatesNames = basketService.getBasketContent(order.getBasket());
            ordersBasket.put(order.getId(), chocolatesNames);
        }
        return ordersBasket;
    }
}
