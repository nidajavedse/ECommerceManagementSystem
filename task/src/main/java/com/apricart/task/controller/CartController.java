package com.apricart.task.controller;

import com.apricart.task.model.Cart;
import com.apricart.task.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        return cartService.updateCart(id, cartDetails);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart("User@123");
    }

    @GetMapping
    public List<Cart> getCartItems() {
        return cartService.getCartItems("User@123");
    }

    @GetMapping("/total")
    public Double getCartTotal() {
        return cartService.getCartTotal("User@123");
    }
}

