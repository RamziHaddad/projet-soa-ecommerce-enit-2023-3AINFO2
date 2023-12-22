package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.api.dtos.OrderPricingDTO;
import org.acme.domain.DiscountRepository;
import org.acme.domain.PriceRepository;
import org.acme.domain.ProductPrice;

import java.math.BigDecimal;
@ApplicationScoped
public class PricingServiceImpl implements PricingService{
    @Inject
    DiscountRepository discountRepository;
    @Inject
    PriceRepository priceRepository;

    @Override
    public BigDecimal priceProducts(OrderPricingDTO orderPricingDTO) {
        return BigDecimal.valueOf(50);
    }
}
