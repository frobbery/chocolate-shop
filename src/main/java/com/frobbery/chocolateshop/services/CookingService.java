package com.frobbery.chocolateshop.services;

import com.frobbery.chocolateshop.repositories.CookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookingService {
    @Autowired
    private CookingRepository cookingRepository;
}
