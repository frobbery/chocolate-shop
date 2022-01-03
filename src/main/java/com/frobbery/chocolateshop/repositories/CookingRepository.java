package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Cooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookingRepository extends JpaRepository<Cooking, Long> {
}
