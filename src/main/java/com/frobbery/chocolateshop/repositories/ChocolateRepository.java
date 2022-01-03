package com.frobbery.chocolateshop.repositories;

import com.frobbery.chocolateshop.entities.Chocolate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChocolateRepository extends JpaRepository<Chocolate, Long> {
    List<Chocolate> getChocolatesThatAreInWarehouse();
}
