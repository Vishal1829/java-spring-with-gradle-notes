package org.learn.springbootlearning.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StripeService {
    public void makePayment(double amount) {
        // Stripe-specific logic to process payment
        log.info("Payment processed via Stripe: {}", amount);
    }
}
