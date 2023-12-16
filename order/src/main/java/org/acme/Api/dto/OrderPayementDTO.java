package org.acme.Api.dto;

import java.math.BigDecimal;
import java.util.UUID;


public record OrderPayementDTO(UUID OrderId, BigDecimal TotalAmount, Long secretCode, Long cartNumber) {
    
}
