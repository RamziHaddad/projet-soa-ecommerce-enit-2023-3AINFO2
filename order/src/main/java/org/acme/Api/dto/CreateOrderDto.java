package org.acme.Api.dto;

import org.acme.domain.Client;
import org.acme.domain.OrderId;
import org.acme.domain.Products;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record CreateOrderDto(UUID orderId, Map<UUID, Integer> products, BigDecimal tatalAmount,UUID clientid, String address ) {
}