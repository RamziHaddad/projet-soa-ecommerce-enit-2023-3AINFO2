package org.acme.Api.dto;

import java.util.Map;
import java.util.UUID;

import org.acme.domain.OrderId;
import org.acme.domain.Products;

public record OrderPrincingDTO(UUID orderId , Map<UUID, Integer> productMap ) {
    
}
