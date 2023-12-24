package org.acme.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface PricingService {
    BigDecimal priceProducts(Map<UUID, Integer> productMap);
}
