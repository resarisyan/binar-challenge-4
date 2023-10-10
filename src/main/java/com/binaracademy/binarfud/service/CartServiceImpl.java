package com.binaracademy.binarfud.service;

import com.binaracademy.binarfud.model.Cart;
import com.binaracademy.binarfud.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public Boolean addNewCart(Cart cart) {
            return Optional.ofNullable(cart)
                    .map(c -> {
                        Cart existingCart = cartRepository.findByProductAndUser(c.getProduct(), c.getUser());
                        if (existingCart != null) {
                            int newQuantity = existingCart.getQuantity() + c.getQuantity();
                            existingCart.setQuantity(newQuantity);
                            double newTotalPrice = existingCart.getTotalPrice() + (c.getProduct().getPrice() * c.getQuantity());
                            existingCart.setTotalPrice(newTotalPrice);
                            cartRepository.save(existingCart);
                        } else {
                            c.setTotalPrice(c.getProduct().getPrice() * c.getQuantity());
                            cartRepository.save(c);
                        }
                        log.info("Cart {} successfully added", c.getId());
                        return true;
                    })
                    .orElseGet(() -> {
                        log.error("Failed to add new cart");
                        return false;
                    });
    }
}
