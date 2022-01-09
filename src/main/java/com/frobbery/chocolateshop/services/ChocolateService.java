package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Chocolate;
import com.frobbery.chocolateshop.repositories.ChocolateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChocolateService {
    private final ChocolateRepository chocolateRepository;

    public ChocolateService(ChocolateRepository chocolateRepository) {
        this.chocolateRepository = chocolateRepository;
        if (chocolateRepository.findAll().isEmpty()) {
            chocolateRepository.save(new Chocolate("Milk", 12));
            chocolateRepository.save(new Chocolate("Dark with almond and raspberry", 0));
            chocolateRepository.save(new Chocolate("Milk with hazelnut and raisin", 1));
            chocolateRepository.save(new Chocolate("Dark with pineapple", 15));
            chocolateRepository.save(new Chocolate("White with coconut", 7));
            chocolateRepository.save(new Chocolate("White with strawberry", 0));
            chocolateRepository.save(new Chocolate("Bitter with coffee", 2));
            chocolateRepository.save(new Chocolate("Milk with waffle", 0));
        }
    }

    public Chocolate[] getBoxTest(boolean result) {
        Chocolate[] box = new Chocolate[4];
        if (result) {
            box[0] = chocolateRepository.findByName("Milk");
            box[1] = chocolateRepository.findByName("Dark with almond and raspberry");
            box[2] = chocolateRepository.findByName("Milk with hazelnut and raisin");
            box[3] = chocolateRepository.findByName("Dark with pineapple");
        }
        else {
            box[0] = chocolateRepository.findByName("White with coconut");
            box[1] = chocolateRepository.findByName("White with strawberry");
            box[2] = chocolateRepository.findByName("Bitter with coffee");
            box[3] = chocolateRepository.findByName("Milk with waffle");
        }
        return box;
    }

    public List<Chocolate> getBoxHand(List<String> chocolatesNames) {
        if (chocolatesNames.size() != 4) {
            return null;
        }
        else {
            List<Chocolate> chocolates = new ArrayList<>();
            for (String name : chocolatesNames) {
                chocolates.add(chocolateRepository.findByName(name));
            }
            return chocolates;
        }
    }

    public Map<String,Integer> getChocolateWithQuantity() {
        Map<String,Integer> chocolates = new HashMap<>();
        for (Chocolate chocolate : chocolateRepository.findAll()) {
            chocolates.put(chocolate.getName(), chocolate.getQuantity());
        }
        return chocolates;
    }

    public List<String> getAllChocolatesNames() {
        List<Chocolate> chocolates = chocolateRepository.findAll();
        List<String> chocolatesNames = new ArrayList<>();
        for (Chocolate chocolate : chocolates) {
            chocolatesNames.add(chocolate.getName());
        }
        return chocolatesNames;
    }

    public String getIntoBox(List<String> chocolatesNames) {
        if (chocolatesNames.size() != 4) {
            return "Select four tastes";
        }
        else {
            for (String chocolateName : chocolatesNames) {
                Chocolate chocolate = chocolateRepository.findByName(chocolateName);
                if (chocolate.getQuantity() < 1) {
                    return "There are not enough quantity of the selected taste.";
                }
            }
            for (String chocolateName : chocolatesNames) {
                Chocolate chocolate = chocolateRepository.findByName(chocolateName);
                chocolate.setQuantity(chocolate.getQuantity() - 1);
                chocolateRepository.save(chocolate);
            }
            return null;
        }
    }
}
