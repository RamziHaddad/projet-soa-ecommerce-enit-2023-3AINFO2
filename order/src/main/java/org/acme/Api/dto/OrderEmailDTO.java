package org.acme.Api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.acme.domain.OrderId;

public record OrderEmailDTO(UUID CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT , boolean Orderstatus) 
{

}



