package com.binaracademy.binarfud.service;

import com.binaracademy.binarfud.model.Cart;
import com.binaracademy.binarfud.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public Boolean addNewCart(Cart cart) {
        Optional.ofNullable(cart)
                .map(Cart::getProduct)
                .map(product -> cartRepository.findByProductAndUser(cart.getProduct(), cart.getUser()))
                .map(existingCart -> {
                    int newQuantity = existingCart.getQuantity() + cart.getQuantity();
                    existingCart.setQuantity(newQuantity);
                    cartRepository.save(existingCart);
                    return true;
                })
                .orElseGet(() -> {
                    cartRepository.save(cart);
                    return true;
                });
        return false;
    }
}
