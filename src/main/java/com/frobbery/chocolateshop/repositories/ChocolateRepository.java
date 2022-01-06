package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Chocolate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChocolateRepository extends JpaRepository<Chocolate, Long> {
    Chocolate findByName(String name);
}
