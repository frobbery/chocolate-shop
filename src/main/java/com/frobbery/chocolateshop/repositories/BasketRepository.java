package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Basket;
import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    //Basket getBasketOfUser(User user);

    //Basket getBasketOfOrder(Order order);
}
