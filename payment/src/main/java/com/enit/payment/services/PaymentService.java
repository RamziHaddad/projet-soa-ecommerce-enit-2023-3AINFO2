package com.enit.payment.services;

import com.enit.payment.PaymentApplication;
import com.enit.payment.model.Payment;
import com.enit.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public void addPayment(Payment P){
        paymentRepository.save(P);
    }
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPayment(Long id){
        return paymentRepository.findById(id).orElse(null);
    }
}
