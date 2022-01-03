package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    String addressOfOrder(Order order);

    String phoneNumberOfOrder(Order order);
}
