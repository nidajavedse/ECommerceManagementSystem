package com.apricart.task.controller;

import com.apricart.task.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/validate")
    public boolean validateCoupon(@RequestParam String couponCode) {
        return couponService.validateCoupon(couponCode);
    }

    @PostMapping("/apply")
    public Double applyCoupon(@RequestParam String couponCode, @RequestParam Double orderTotal) {
        return couponService.applyCoupon(couponCode, orderTotal);
    }
}
