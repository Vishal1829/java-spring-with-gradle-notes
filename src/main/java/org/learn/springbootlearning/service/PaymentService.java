package org.learn.springbootlearning.service;

import org.learn.springbootlearning.designpattern.adapter.PaymentProcessor;
import org.learn.springbootlearning.request.PaymentRequest;
import org.learn.springbootlearning.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    //Key - PayPalAdapter , Value - PayPalAdapter.class
    private final Map<String, PaymentProcessor> paymentProcessorMap;

    public PaymentService(List<PaymentProcessor> paymentProcessors) {
        paymentProcessorMap = paymentProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getClass().getSimpleName(), Function.identity()));
    }

    public PaymentResponse processPayment(String gateway, PaymentRequest paymentRequest) {
        PaymentProcessor paymentProcessor = paymentProcessorMap.get(gateway + "Adapter");
        paymentProcessor.makePayment(paymentRequest.amount());
        return new PaymentResponse(true, new Random().nextLong(1000000000L));
    }

//    @Autowired
//    private PayPalService payPalService;
//
//    @Autowired
//    private StripeService stripeService;
//
//    public PaymentResponse processPayment(String gateway, PaymentRequest paymentRequest) {
//        switch (gateway.toLowerCase()) {
//            case "paypal":
//                payPalService.makePayment(paymentRequest.amount());
//                break;
//            case "stripe":
//                stripeService.makePayment(paymentRequest.amount());
//                break;
//        }
//        return new PaymentResponse(true, new Random().nextLong(100000000L));
//    }

//    private PaymentProcessor getPaymentGatewayInstance(String gateway) {
//        PaymentProcessor processor = null;
//        switch (gateway.toLowerCase()) {
//            case "paypal":
//                processor = new PayPalAdapter();
//                break;
//            case "stripe":
//                processor = new StripeAdapter();
//                break;
//            case "gpay":
//                processor = new GPayAdapter();
//                break;
//        }
//        return processor;
//    }
}
/*
The Adapter Design Pattern is a structural design pattern used to allow to incompatible interfaces to work together.
The pattern acts as a bridge between a client and a service, making them compatible without changing their existing code.

Before using adapter design pattern here the commented code:
we have to add another switch case for a new payment provider.

After using adapter design pattern we just have to add the new payment provider class which implements the
paymentProcessor interface that's it.
 */

/*
These implementation classes are detected by Spring at runtime via classpath scanning,
assuming they are annotated with @Component or @Service and placed in a package that's scanned by Spring.

Spring Dependency Injection for List of PaymentProcessor
In the PaymentService constructor, Spring sees that it requires a List<PaymentProcessor>.
Spring automatically collects all the beans (implementations) of the PaymentProcessor interface and provides them as a list.
The paymentProcessors list will contain all PaymentProcessor implementations (e.g., PayPalAdapter, StripeAdapter, etc.)
that are registered as Spring beans.
 */