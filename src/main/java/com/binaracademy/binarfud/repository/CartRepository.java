package com.binaracademy.binarfud.repository;

import com.binaracademy.binarfud.model.Cart;
import com.binaracademy.binarfud.model.Product;
import com.binaracademy.binarfud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByProductAndUser(Product product, User user);
    List<Cart> findByUser(User user);
}
