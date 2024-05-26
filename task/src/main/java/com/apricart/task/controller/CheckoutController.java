package com.apricart.task.controller;

import com.apricart.task.model.Checkout;
import com.apricart.task.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping
    public Checkout processCheckout(@RequestParam String userId,
                                    @RequestParam(required = false) String couponCode,
                                    @RequestParam String paymentMethod) {
        return checkoutService.processCheckout(userId, couponCode, paymentMethod);
    }
}
