package com.enit.payment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
public class PaymentRequest{
    private UUID id ;
    private BigDecimal amount;
    private Long cartNumber;
    private Long secretCode;
}


