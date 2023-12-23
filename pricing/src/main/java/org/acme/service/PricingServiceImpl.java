package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.api.dtos.OrderPricingDTO;
import org.acme.domain.Discount;
import org.acme.domain.DiscountRepository;
import org.acme.domain.PriceRepository;
import org.acme.domain.ProductPrice;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PricingServiceImpl implements PricingService{
    @Inject
    DiscountRepository discountRepository;
    @Inject
    PriceRepository priceRepository;

    @Override
    public BigDecimal priceProducts(Map<UUID, Integer> productMap) {
        BigDecimal sum = BigDecimal.ZERO;
        for (UUID productID : productMap.keySet()) {
            BigDecimal quantity = BigDecimal.valueOf(productMap.get(productID));
            Optional<ProductPrice> productPriceOptional = priceRepository.getProductPrice(productID);
            if (productPriceOptional.isPresent()) {
                BigDecimal price = productPriceOptional.get().getBasePrice();
                Optional<Discount> discountOptional = discountRepository.getBestValidDiscount(productID);
                if (discountOptional.isPresent()) {
                    BigDecimal discountFactor = discountOptional.get().getDiscountFactor();
                    price = price.multiply(discountFactor);
                }
                sum = sum.add(price.multiply(quantity));
            }
        }
        return sum;
    }
}
