package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrderByStatus_Registered();

    List<Order> getOrderByStatus_Paid();

    List<Order> getOrderByStatus_Gathered();

    List<Order> getOrderByUser(User user);
}
