package com.nt.service;


import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void placeOrder(String productId) {
        validateStock(productId);
        sendConfirmationEmail(productId); // external call
    }

    public void validateStock(String productId) {
        // Checks DB for inventory
        System.out.println("stock is available:"+productId);
    }

    public void sendConfirmationEmail(String productId) {
        // Actually sends email
        System.out.println("email sent......");
    }
}
