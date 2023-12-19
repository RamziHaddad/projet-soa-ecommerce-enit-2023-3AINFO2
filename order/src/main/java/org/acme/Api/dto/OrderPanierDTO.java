package org.acme.Api.dto;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record OrderPanierDTO(UUID OrderId, Map<UUID, Integer> items, BigDecimal Totalamount, Long secretCode, Long cartNumber,UUID ClientId, String address) 
{

}
