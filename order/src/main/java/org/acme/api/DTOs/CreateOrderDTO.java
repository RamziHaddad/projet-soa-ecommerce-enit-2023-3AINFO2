package org.acme.api.DTOs;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record CreateOrderDTO(UUID orderId, Map<UUID, Integer> products, BigDecimal totalAmount,UUID clientid, String address ) {
}