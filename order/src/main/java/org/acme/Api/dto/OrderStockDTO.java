package org.acme.Api.dto;

import java.util.Map;
import java.util.UUID;

public record OrderStockDTO(UUID orderId, Map<UUID, Integer> productMap) {

}
