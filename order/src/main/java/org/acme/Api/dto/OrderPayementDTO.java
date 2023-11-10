package org.acme.Api.dto;

import java.math.BigDecimal;

import org.acme.domain.Client;
import org.acme.domain.CommandeId;

public record CommandePayementDTO(CommandeId commandeId, BigDecimal TotalAmount, Client clintInfo) {
    
}
