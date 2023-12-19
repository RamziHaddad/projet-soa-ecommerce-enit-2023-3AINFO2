package com.enit.payment.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentRequest{
    private UUID OrderId ;
    private BigDecimal TotalAmount;
    private Long secretCode;
    private Long cartNumber;
}


