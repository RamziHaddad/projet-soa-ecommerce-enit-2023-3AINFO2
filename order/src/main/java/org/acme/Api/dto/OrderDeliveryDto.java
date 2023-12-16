package org.acme.Api.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record OrderDeliveryDto(UUID orderId, Map<UUID, Integer> productMap ,BigDecimal tatalAmount, String ville, String codePostal, String rue) {
}
