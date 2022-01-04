package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Basket;
import com.frobbery.chocolateshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrPhoneNumber(String email, Long phoneNumber);

    Optional<User> findByPhoneNumber(Long phoneNumber);
}
