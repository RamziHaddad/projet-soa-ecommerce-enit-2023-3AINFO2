package com.enit.payment.controller;

import com.enit.payment.dto.PaymentRequest;
import com.enit.payment.model.Payment;
import com.enit.payment.services.BankService;
import com.enit.payment.services.PaymentService;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.scheduling.annotation.EnableScheduling;
@RestController
@RequestMapping("/api/payment")

public class PaymentController {
        private final PaymentService paymentService;
        private final BankService banque;
        public PaymentController(PaymentService paymentService,BankService banque) {
            this.paymentService = paymentService;
            this.banque = banque;
        }
        @GetMapping("/getAllPayment/")
        public List<Payment> getAllPayments(){
            return paymentService.getAllPayments();
        }

        @GetMapping("/{id}")
        public Payment getPayment(@PathVariable("id") Long id){
            return paymentService.getPayment(id);
        }

        @PostMapping("/TimeOutPayment")
        @Scheduled(fixedRate = 20000) //timeout pattern 20 seconds
        public Response startPaymentTimeout(PaymentRequest p){
            Payment paymentAdded = Payment.builder()
                    .id(p.getId())
                    .amount(p.getAmount())
                    .cardNumber(p.getCartNumber())
                    .secretCode(p.getSecretCode())
                    .build();
            Response res = banque.makeNewPayment(p);
            if(res.status() >= 200 && res.status() < 300){
                paymentService.addPayment(paymentAdded);
            }
            return res;
        }
        int maxRetries = 5;
        long retryDelayMillis;
        @PostMapping("/RetryPayment")  //with retry pattern
        public Response startPaymentRetry(PaymentRequest p) {
            int retries = 0;
            Response res;
            do {
                Payment paymentAdded = Payment.builder()
                        .id(p.getId())
                        .amount(p.getAmount())
                        .cardNumber(p.getCartNumber())
                        .secretCode(p.getSecretCode())
                        .build();
                res = banque.makeNewPayment(p);
                if (res.status() >= 200 && res.status() < 300) {
                    paymentService.addPayment(paymentAdded);
                    break;  // Payment succeeded, exit the loop
                }
                // Payment failed, retry after a delay
                retries++;
                if (retries <= maxRetries) {
                    try {
                        Thread.sleep(retryDelayMillis);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    break;// Max retries reached, break out of the loop
                }
            } while (true);
            return res;
        }
}
