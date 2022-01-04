package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Chocolate;
import com.frobbery.chocolateshop.entities.Cooking;
import com.frobbery.chocolateshop.repositories.ChocolateRepository;
import com.frobbery.chocolateshop.repositories.CookingRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
            return "Такого вкуса не существует";
        }
        else {
            cookingRepository.save(new Cooking(chocolate, quantity));
            return null;
        }
    }

    public String updateCookingAndChocolate(Long id, Integer quantity) {
        Optional<Cooking> cookingOptional = cookingRepository.findById(id);
        if (cookingOptional.isEmpty()) {
            return "Запроса с таким id не существует";
        }
        else {
            Cooking cooking = cookingOptional.get();
            Chocolate chocolate = cooking.getChocolate();
            cookingRepository.delete(cooking);
            chocolate.setQuantity(chocolate.getQuantity() + quantity);
            return null;
        }
    }

    public Map<String,Integer> getAllCookingOrders() {
        Map<String,Integer> cookingOrders = new HashMap<>();
        for (Cooking cooking : cookingRepository.findAll()) {
            cookingOrders.put(cooking.getChocolate().getName(), cooking.getQuantity());
        }
        return cookingOrders;
    }
}
