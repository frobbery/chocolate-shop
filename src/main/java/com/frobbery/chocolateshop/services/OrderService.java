package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.Status;
import com.frobbery.chocolateshop.entities.User;
import com.frobbery.chocolateshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;

    public OrderService(OrderRepository orderRepository, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.basketService = basketService;
    }

    public void createOrder(User user, String address) {
        orderRepository.save(new Order(user, address));
    }

    public Map<Long,String> getOrdersOfUser(User user) {
        List<Order> ordersOfUser = orderRepository.getByUser(user);
        Map<Long,String> orders = new HashMap<>();
        for (Order order : ordersOfUser) {
            orders.put(order.getId(), order.getStatus().toString());
        }
        return orders;
    }

    public Map<Long,List<String>> getPaidOrdersBasket() {
        Map<Long,List<String>> ordersBasket = new HashMap<>();
        List<Order> paidOrders = orderRepository.findByStatusIs(Status.PAID);
        for (Order order : paidOrders) {
            List<String> chocolatesNames = basketService.getBasketContent(order.getBasket());
            ordersBasket.put(order.getId(), chocolatesNames);
        }
        return ordersBasket;
    }

    public String updateOrderStatusToGathered(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            return "There is no order with such id";
        }
        else {
            Order order = orderOptional.get();
            if (!order.getStatus().equals(Status.PAID)) {
                return "Order is either hasn't been paid or is already gathered";
            }
            else {
                order.setStatus(Status.GATHERED);
                return null;
            }
        }
    }

    public Map<Long,Long> getRegisteredOrdersWithPhone() {
        Map<Long,Long> ordersBasket = new HashMap<>();
        List<Order> paidOrders = orderRepository.findByStatusIs(Status.REGISTERED);
        for (Order order : paidOrders) {
            ordersBasket.put(order.getId(), order.getUser().getPhoneNumber());
        }
        return ordersBasket;
    }

    public String updateOrderStatusToPaid(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            return "There is no order with such id";
        }
        else {
            Order order = orderOptional.get();
            if (!order.getStatus().equals(Status.REGISTERED)) {
                return "Order is already paid";
            }
            else {
                order.setStatus(Status.PAID);
                return null;
            }
        }
    }

    public Map<Long, String> getGatheredOrdersWithAddress() {
        Map<Long,String> ordersBasket = new HashMap<>();
        List<Order> paidOrders = orderRepository.findByStatusIs(Status.GATHERED);
        for (Order order : paidOrders) {
            ordersBasket.put(order.getId(), order.getAddress());
        }
        return ordersBasket;
    }

    public String updateOrderStatusToSent(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            return "There is no order with such id";
        }
        else {
            Order order = orderOptional.get();
            if (!order.getStatus().equals(Status.GATHERED)) {
                return "Order is either hasn't been gathered or is already sent";
            }
            else {
                order.setStatus(Status.SENT);
                return null;
            }
        }
    }
}
