package com.enit.payment.services;


import com.enit.payment.dto.PaymentRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "bank-service", url = "http://localhost:8099/payment")
public interface BankService {
    @GetMapping("/{id}")
    BigDecimal withdrawMoneyFromAccount(@RequestParam("money") BigDecimal money, @PathVariable("id") Long id);

    @PostMapping
    public Response makeNewPayment(PaymentRequest cmd);


}
