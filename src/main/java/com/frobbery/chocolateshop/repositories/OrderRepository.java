package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getRegisteredOrders();

    List<Order> getPaidOrders();

    List<Order> getGatheredOrders();

    List<Order> getOrdersOfUser(User user);
}
