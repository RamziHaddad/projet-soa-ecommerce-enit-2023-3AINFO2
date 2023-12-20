package com.enit.payment.controller;

import com.enit.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")

public class PaymentRepository {
    private final PaymentService paymentService;

    public PaymentRepository(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
