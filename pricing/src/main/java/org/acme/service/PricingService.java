package org.acme.service;

import org.acme.api.dtos.OrderPricingDTO;

import java.math.BigDecimal;

public interface PricingService {
    BigDecimal priceProducts(OrderPricingDTO orderPricingDTO);
}
