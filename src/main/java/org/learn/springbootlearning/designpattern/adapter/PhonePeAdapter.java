package org.learn.springbootlearning.designpattern.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PhonePeAdapter implements PaymentProcessor {
    @Override
    public void makePayment(double amount) {
        //call actual phonePe api
        log.info("Payment processed via PhonePe: {}", amount);
    }
}
