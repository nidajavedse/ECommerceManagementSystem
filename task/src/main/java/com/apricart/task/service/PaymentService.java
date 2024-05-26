package com.apricart.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public boolean processPayment(String userId, Double amount, String paymentMethod) {
        logger.info("Processing payment for user: {} with amount: {} using method: {}", userId, amount, paymentMethod);
        // Simulate payment processing
        // In a real application, you would integrate with a payment gateway here.
        boolean paymentSuccess = true; // Assume payment is successful
        if (paymentSuccess) {
            logger.info("Payment successful for user: {}", userId);
        } else {
            logger.error("Payment failed for user: {}", userId); // If payment fails
        }
        return paymentSuccess;
    }
}
