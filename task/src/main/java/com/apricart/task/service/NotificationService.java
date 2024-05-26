package com.apricart.task.service;

import com.apricart.task.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendOrderConfirmation(String userId, Order order) {
        logger.info("Sending order confirmation to user {} for order {}", userId, order.getOrderId());
        // In a real application, use an email service to send the email
        System.out.println("Order confirmation email sent to user " + userId + " for order " + order.getOrderId());
    }

    public void sendPaymentConfirmation(String userId, Order order) {
        logger.info("Sending payment confirmation to user {} for order {}", userId, order.getOrderId());
        // In a real application, use an email service to send the email
        System.out.println("Payment confirmation email sent to user " + userId + " for order " + order.getOrderId());
    }
}
