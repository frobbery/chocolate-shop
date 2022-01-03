package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Chocolate;
import com.frobbery.chocolateshop.repositories.ChocolateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChocolateService {
    @Autowired
    private ChocolateRepository chocolateRepository;

    public ChocolateService(ChocolateRepository chocolateRepository) {
        this.chocolateRepository = chocolateRepository;
        if (chocolateRepository.findAll().isEmpty()) {
            chocolateRepository.save(new Chocolate("Молочный", 12));
            chocolateRepository.save(new Chocolate("Горький с миндалем и малиной", 0));
            chocolateRepository.save(new Chocolate("Молочный с фундуком и изюмом", 1));
            chocolateRepository.save(new Chocolate("Темный с ананасом", 15));
            chocolateRepository.save(new Chocolate("Белый с кокосовой стружкой", 7));
            chocolateRepository.save(new Chocolate("Белый с земляникой", 0));
            chocolateRepository.save(new Chocolate("Горький с кофе", 2));
            chocolateRepository.save(new Chocolate("Молочный с вафлей", 0));
        }
    }

    public Chocolate[] getBoxTest(boolean result) {
        Chocolate[] box = new Chocolate[4];
        if (result) {
            box[0] = chocolateRepository.getChocolateByName("Молочный");
            box[1] = chocolateRepository.getChocolateByName("Горький с миндалем и малиной");
            box[2] = chocolateRepository.getChocolateByName("Молочный с фундуком и изюмом");
            box[3] = chocolateRepository.getChocolateByName("Темный с ананасом");
        }
        else {
            box[0] = chocolateRepository.getChocolateByName("Белый с кокосовой стружкой");
            box[1] = chocolateRepository.getChocolateByName("Белый с земляникой");
            box[2] = chocolateRepository.getChocolateByName("Горький с кофе");
            box[3] = chocolateRepository.getChocolateByName("Молочный с вафлей");
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
                chocolates.add(chocolateRepository.getChocolateByName(name));
            }
            return chocolates;
        }
    }

    public List<String> catalogue() {
        List<Chocolate> catalogue = chocolateRepository.findAll();
        List<String> names = new ArrayList<>();

        for (Chocolate chocolate : catalogue) {
            names.add(chocolate.getName());
        }
        return names;
    }

    public Map<String,Integer> getAvailableChocolate() {
        List<Chocolate> availableChocolates = chocolateRepository.getChocolateByQuantityIsNotNull();
        Map<String,Integer> chocolates = new HashMap<>();
        for (Chocolate chocolate : availableChocolates) {
            chocolates.put(chocolate.getName(), chocolate.getQuantity());
        }
        return chocolates;
    }
}
