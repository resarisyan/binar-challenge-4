package com.binaracademy.binarfud.service;

import com.binaracademy.binarfud.model.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Boolean addNewCart(Cart cart);
}
