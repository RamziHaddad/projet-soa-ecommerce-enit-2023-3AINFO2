package org.acme;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record OrderPanierDTO(
        UUID orderId,
        Map<UUID, Integer> items,
        BigDecimal totalAmount,
        Long secretCode,
        Long cartNumber,
        UUID clientId,
        Long address
        ) {

        }
