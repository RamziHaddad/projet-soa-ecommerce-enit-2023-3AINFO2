package org.acme.Api.dto;

import java.math.BigDecimal;

import org.acme.domain.Client;
import org.acme.domain.OrderId;

public record OrderPayementDTO(OrderId OrderId, BigDecimal TotalAmount, Client clintInfo) {
    
}
