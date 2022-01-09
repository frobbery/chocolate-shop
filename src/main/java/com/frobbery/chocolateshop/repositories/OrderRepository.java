package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Order;
import com.frobbery.chocolateshop.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.status = ?1")
    List<Order> findByStatusIs(Status status);

    List<Order> findByUserId(Long id);
}
