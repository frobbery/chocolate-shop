package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Cooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingRepository extends JpaRepository<Cooking, Long> {
}
