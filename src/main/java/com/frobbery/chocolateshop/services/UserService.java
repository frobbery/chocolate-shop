package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.entities.Basket;
import com.frobbery.chocolateshop.entities.User;
import com.frobbery.chocolateshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BasketService basketService;
    private OrderService orderService;

    public UserService(UserRepository userRepository, BasketService basketService, OrderService orderService) {
        this.userRepository = userRepository;
        this.basketService = basketService;
        this.orderService = orderService;
        if (userRepository.findAll().isEmpty()) {
            userRepository.save(new User("ADMIN", "ADMIN", 1L));
            userRepository.save(new User("CHEF", "CHEF", 1L));
            userRepository.save(new User("WORKER", "WORKER", 1L));
        }
    }

    private String register(String email, String password, Long phoneNumber) {
        Optional<User> user = userRepository.findByEmailOrPhoneNumber(email, phoneNumber);
        if (user.isPresent()) {
            return "Пользователь с таким email или/и номером телефона уже зарегистрирован";
        }
        else {
            userRepository.save(new User(email, password, phoneNumber));
            return null;
        }
    }

    public String authorize(Long phoneNumber, String password) {
        Optional<User> userWithPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userWithPhoneNumber.isEmpty()) {
            return "Пользователя с таким номером телефона не существует";
        }
        else {
            User user = userWithPhoneNumber.get();
            if (!user.getPassword().equals(password)) {
                return "Введен неверный пароль";
            }
            else {
                if (user.isAdmin()) {
                    return "AdminRole";
                }
                else if (user.isChef()) {
                    return "ChefRole";
                }
                else if (user.isWorker()) {
                    return "WorkerRole";
                }
                else {
                    return "UserRole";
                }
            }
        }
    }

    public String setBasketFromTest(User user, boolean result) {
        if (user.getBasket() != null) {
            return "Корзина не пуста";
        }
        else {
            user.setBasket(basketService.createBasketFromTest(result));
            return null;
        }
    }

    public String setBasketFromHand(User user, List<String> chocolatesNames) {
        if (user.getBasket() != null) {
            return "Корзина не пуста";
        }
        else {
            Basket basket = basketService.createBasketByHand(chocolatesNames);
            if (basket == null) {
                return "Выбрано не четыре вкуса";
            }
            else {
                user.setBasket(basket);
                return null;
            }
        }
    }

    public List<String> basket(User user) {
        List<String> chocolatesNames = new ArrayList<>();
        Basket basket = user.getBasket();
        if (basket == null) {
            for (int i = 0; i < 4; i++) {
                chocolatesNames.add("-");
            }
        }
        else {
            chocolatesNames = basketService.getBasketContent(basket);
        }
        return chocolatesNames;
    }

    public String cleanBasket(User user) {
        Basket basket = user.getBasket();
        if (basket == null) {
            return "Корзина пуста";
        }
        else {
            user.setBasket(null);
            basketService.deleteBasket(basket);
            return null;
        }
    }

    public String placeOrder(User user, String address) {
        if (user.getBasket() == null) {
            return "Корзина пуста";
        }
        else {
            orderService.createOrder(user, address);
            user.setBasket(null);
            return null;
        }
    }
}
