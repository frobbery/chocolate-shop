package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Basket;
import com.frobbery.chocolateshop.entities.Chocolate;
import com.frobbery.chocolateshop.repositories.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private  final ChocolateService chocolateService;

    public BasketService(BasketRepository basketRepository, ChocolateService chocolateService) {
        this.basketRepository = basketRepository;
        this.chocolateService = chocolateService;
    }

    public Basket createBasketFromTest(boolean result) {
        Chocolate[] box;
        box = chocolateService.getBoxTest(result);
        Basket basket = new Basket(box[0], box[1], box[2], box[3]);
        basketRepository.save(basket);
        return basket;
    }

    public Basket createBasketByHand(List<String> chocolatesNames) {
        List<Chocolate> chocolates = chocolateService.getBoxHand(chocolatesNames);
        if (chocolates == null) {
            return null;
        }
        else {
            Basket basket = new Basket(
                    chocolates.get(0),
                    chocolates.get(1),
                    chocolates.get(2),
                    chocolates.get(3)
            );
            basketRepository.save(basket);
            return basket;
        }
    }

    public void deleteBasket(Basket basket) {
        basketRepository.delete(basket);
    }

    public List<String> getBasketContent(Basket basket) {
        List<String> chocolatesNames = new ArrayList<>();
        chocolatesNames.add(basket.getChocolate1().getName());
        chocolatesNames.add(basket.getChocolate2().getName());
        chocolatesNames.add(basket.getChocolate3().getName());
        chocolatesNames.add(basket.getChocolate4().getName());
        return chocolatesNames;
    }
}
