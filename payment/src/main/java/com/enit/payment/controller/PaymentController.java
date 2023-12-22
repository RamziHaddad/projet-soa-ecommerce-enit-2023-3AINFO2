package com.enit.payment.controller;

import com.enit.payment.dto.PaymentRequest;
import com.enit.payment.model.Payment;
import com.enit.payment.services.BankService;
import com.enit.payment.services.PaymentService;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payment")

public class PaymentController {
        private final PaymentService paymentService;
        //private final BankService banque;
        public PaymentController(PaymentService paymentService) {
            this.paymentService = paymentService;
            //this.banque = banque;
        }
        @GetMapping("/getAllPayment/")
        public List<Payment> getAllPayments(){
            return paymentService.getAllPayments();
        }

        @GetMapping("/{id}")
        public Payment getPayment(@PathVariable("id") Long id){
            return paymentService.getPayment(id);
        }

        /*
        @PostMapping
        public Response startPayment(PaymentRequest p){
            Payment paymentAdded = new Payment(p.getId(),p.getAmount(),p.getcardNumber(),p.getSecretCode());
            Payment paymentAdded = Payment.builder();
            banque.makeNewPayment(paymentAdded);
            paymentService.addPayment(paymentAdded);
            return ResponseEntity.ok(Response.OK(paymentAdded).build());
            //to do: save in bd
        }
        */
}
