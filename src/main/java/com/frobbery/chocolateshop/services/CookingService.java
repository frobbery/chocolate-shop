package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Chocolate;
import com.frobbery.chocolateshop.entities.Cooking;
import com.frobbery.chocolateshop.repositories.ChocolateRepository;
import com.frobbery.chocolateshop.repositories.CookingRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CookingService {
    private final CookingRepository cookingRepository;
    private final ChocolateRepository chocolateRepository;

    public CookingService(CookingRepository cookingRepository, ChocolateRepository chocolateRepository) {
        this.cookingRepository = cookingRepository;
        this.chocolateRepository = chocolateRepository;
    }

    public String addCookingOrder(String taste, Integer quantity) {
        Chocolate chocolate = chocolateRepository.findByName(taste);
        if (chocolate == null) {
            return "There is no taste with such name";
        }
        else {
            cookingRepository.save(new Cooking(chocolate, quantity));
            return null;
        }
    }

    public String updateCookingAndChocolate(Long id, Integer quantity) {
        Optional<Cooking> cookingOptional = cookingRepository.findById(id);
        if (cookingOptional.isEmpty()) {
            return "There is no request with such id";
        }
        else {
            Cooking cooking = cookingOptional.get();
            Chocolate chocolate = cooking.getChocolate();
            cookingRepository.delete(cooking);
            chocolate.setQuantity(chocolate.getQuantity() + quantity);
            chocolateRepository.save(chocolate);
            return null;
        }
    }

    public Map<List<String>,Integer> getAllCookingOrders() {
        Map<List<String>,Integer> cookingOrders = new HashMap<>();
        for (Cooking cooking : cookingRepository.findAll()) {
            List<String> idAndName = new ArrayList<>();
            idAndName.add(cooking.getId().toString());
            idAndName.add(cooking.getChocolate().getName());
            cookingOrders.put(idAndName, cooking.getQuantity());
        }
        return cookingOrders;
    }
}
