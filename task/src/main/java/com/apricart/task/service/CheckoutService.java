package com.apricart.task.service;

import com.apricart.task.exception.InvalidCouponException;
import com.apricart.task.model.*;
import com.apricart.task.repository.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class CheckoutService {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public Checkout processCheckout(String userId, String couponCode, String paymentMethod) {
        logger.info("Processing checkout for user: {}", userId);
        Double orderTotal = calculateTotal(userId, couponCode);

        // Process payment
        boolean paymentSuccess = paymentService.processPayment(userId, orderTotal, paymentMethod);
        if (!paymentSuccess) {
            logger.error("Payment processing failed for user: {}", userId);
            throw new RuntimeException("Payment processing failed");
        }

        // Generate unique order ID
        String orderId = UUID.randomUUID().toString();

        // Create order
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderId(orderId);
        order.setTotalPrice(orderTotal);
        order.setStatus("Confirmed");
        orderRepository.save(order);
        logger.info("Order created for user: {}, Order ID: {}", userId, order.getOrderId());

        // Create checkout
        Checkout checkout = new Checkout();
        checkout.setUserId(userId);
        checkout.setTotalAmount(orderTotal);
        checkout.setOrder(order);
        logger.info("Checkout completed for user: {}", userId);

        checkoutRepository.save(checkout);

        // Send notifications
        notificationService.sendOrderConfirmation(userId, order);
        notificationService.sendPaymentConfirmation(userId, order);

        return checkout;
    }

    private Double calculateTotal(String userId, String couponCode) {
        List<Cart> cartItems = cartRepository.findAllByUserId(userId);
        double subtotal = 0.0;
        for (Cart cartItem : cartItems) {
            subtotal += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        logger.info("Calculated subtotal for user: {}: {}", userId, subtotal);

        double discount = 0.0;
        if (couponCode != null && !couponCode.isEmpty()) {
            Coupon coupon = couponRepository.findByCouponCode(couponCode)
                    .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
            logger.info("Applying coupon: {}", couponCode);

            if (coupon.getActive() && subtotal >= coupon.getMinOrderAmount()) {
                discount = coupon.getDiscountAmount();
            } else {
                logger.error("Invalid or inactive coupon: {}", couponCode);
                throw new InvalidCouponException("Invalid or inactive coupon");
            }
        }

        double taxes = calculateTaxes(subtotal - discount);
        double total = subtotal - discount + taxes;
        logger.info("Calculated total for user: {}: {}", userId, total);
        return total;
    }

    private double calculateTaxes(double amount) {
        double taxRate = 0.08; // Example tax rate of 8%
        return amount * taxRate;
    }
}
