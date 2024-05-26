package com.apricart.task.service;

import com.apricart.task.model.Cart;
import com.apricart.task.repository.CartRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public Cart addToCart(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        logger.info("Product added to cart successfully: {}", savedCart);
        return savedCart;
    }

    public Cart updateCart(Long cartId, Cart cartDetails) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.setProduct(cartDetails.getProduct());
        cart.setQuantity(cartDetails.getQuantity());
        cart.setWarehouse(cartDetails.getWarehouse());
        Cart updatedCart = cartRepository.save(cart);
        logger.info("Cart updated successfully: {}", updatedCart);
        return cartRepository.save(cart);
    }

    public void removeFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartRepository.delete(cart);
        logger.info("Cart removed successfully with ID: {}", cartId);
    }

    public void clearCart(String userId) {
        List<Cart> cartItems = cartRepository.findAll().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .collect(Collectors.toList());
        cartRepository.deleteAll(cartItems);
        logger.info("Cart cleared successfully for user with ID: {}", userId);

    }

    public List<Cart> getCartItems(String userId) {
        logger.info("Retrieving cart items for user with ID: {}", userId);
        List<Cart> cartItems = cartRepository.findAll().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .collect(Collectors.toList());
        logger.info("Cart items retrieved successfully for user with ID: {}", userId);
        return cartItems;
    }

    public Double getCartTotal(String userId) {
        logger.info("Calculating total for cart items of user with ID: {}", userId);
        Double total = getCartItems(userId).stream()
                .mapToDouble(cart -> cart.getQuantity() * cart.getProduct().getPrice())
                .sum();
        logger.info("Total calculated successfully for user with ID {}: {}", userId, total);
        return total;
    }
}
