package org.acme.Api.dto;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record OrderPanierDTO(UUID CommandeId, Map<UUID, Integer> items, BigDecimal montantTotal, Long secretCode, Long cartNumber ) 
{

}
