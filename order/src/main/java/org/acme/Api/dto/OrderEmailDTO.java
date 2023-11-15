package org.acme.Api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.acme.domain.OrderId;

public record OrderEmailDTO(OrderId CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT , boolean Orderstatus) 
{

}



