package com.apricart.task.service;

import com.apricart.task.exception.InvalidCouponException;
import com.apricart.task.model.Coupon;
import com.apricart.task.repository.CouponRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    public boolean validateCoupon(String couponCode) {
        Coupon coupon = couponRepository.findByCouponCode(couponCode)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
        return coupon.getActive();
    }

    public Double applyCoupon(String couponCode, Double orderTotal) {
        Coupon coupon = couponRepository.findByCouponCode(couponCode)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
        if (validateCoupon(couponCode) && orderTotal >= coupon.getMinOrderAmount()) {
            return orderTotal - coupon.getDiscountAmount();
        }
        throw new InvalidCouponException("Invalid or inactive coupon");
    }
}
