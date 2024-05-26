package com.apricart.task.service;
import com.apricart.task.model.Order;
import com.apricart.task.repository.OrderRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        logger.info("Placing order: {}", order);
        Order placedOrder = orderRepository.save(order);
        logger.info("Order placed successfully: {}", placedOrder);
        return placedOrder;
    }

    public Order updateOrderStatus(Long orderId, String status) {
        logger.info("Updating order status for order ID {}: {}", orderId, status);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        logger.info("Order status updated successfully for order ID {}: {}", orderId, status);
        return updatedOrder;
    }

    public List<Order> getOrderByUserId(String userId) {
        logger.info("Retrieving orders for user with ID: {}", userId);
        List<Order> orders = orderRepository.findAll().stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
        logger.info("Orders retrieved successfully for user with ID: {}", userId);
        return orders;
    }

    public Order getOrderById(Long orderId) {
        logger.info("Retrieving order by ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        logger.info("Order retrieved successfully by ID: {}", orderId);
        return order;
    }

    public void cancelOrder(Long orderId) {
        logger.info("Cancelling order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
        logger.info("Order cancelled successfully with ID: {}", orderId);
    }

    public Double getOrderTotal(Long orderId) {
        logger.info("Calculating total for order with ID: {}", orderId);
        Order order = getOrderById(orderId);
        Double total = order.getQuantity() * order.getProduct().getPrice();
        logger.info("Total calculated successfully for order with ID {}: {}", orderId, total);
        return total;
    }
}
