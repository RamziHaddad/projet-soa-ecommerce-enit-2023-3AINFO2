package org.acme.Api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.acme.domain.CommandeId;

public record CommandeEmailDTO(CommandeId CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT ) 
{

}



