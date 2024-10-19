package org.learn.springbootlearning.designpattern.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayPalAdapter implements PaymentProcessor {
    public void makePayment(double amount) {
        // PayPal-specific logic to process payment
        //actual api
        log.info("Payment processed via PayPal: {}", amount);
    }
}
