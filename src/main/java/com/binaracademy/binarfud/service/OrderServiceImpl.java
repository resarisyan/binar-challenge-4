package com.binaracademy.binarfud.service;

import com.binaracademy.binarfud.model.Order;
import com.binaracademy.binarfud.model.OrderDetail;
import com.binaracademy.binarfud.repository.CartRepository;
import com.binaracademy.binarfud.repository.OrderDetailRepository;
import com.binaracademy.binarfud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Boolean addNewOrder(Order order) {
        return Optional.ofNullable(order)
                .map(user -> cartRepository.findByUser(order.getUser()))
                .map(carts -> {
                    Order newOrder = new Order();
                    newOrder.builder()
                            .destinationAddress(order.getDestinationAddress())
                            .completed(false)
                            .orderTime(new Date())
                            .user(order.getUser())
                            .note(order.getNote())
                            .build();
                    orderRepository.save(newOrder);

                    carts.forEach(cart -> {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.builder()
                                .order(newOrder)
                                .product(cart.getProduct())
                                .quantity(cart.getQuantity())
                                .totalPrice(cart.getTotalPrice())
                                .build();
                        orderDetailRepository.save(orderDetail);
                    });
                    return true;
                })
                .orElse(false);
    }
}
