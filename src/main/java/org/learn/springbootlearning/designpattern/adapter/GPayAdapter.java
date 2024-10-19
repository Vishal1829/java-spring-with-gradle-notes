package org.learn.springbootlearning.designpattern.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GPayAdapter implements PaymentProcessor {
    public void makePayment(double amount) {
        // GPay-specific logic to process payment
        log.info("Payment processed via GPay: {}", amount);
    }
}
