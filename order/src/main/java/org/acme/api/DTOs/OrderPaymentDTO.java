package org.acme.api.DTOs;

import java.math.BigDecimal;
import java.util.UUID;
public record OrderPaymentDTO(UUID OrderId, BigDecimal TotalAmount, Long secretCode, Long cartNumber) {

}