
package org.acme.service;

import org.acme.api.dtos.OrderPricingDTO;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface ProductPricingStrategy {
    BigDecimal calculateProductPrice(UUID productId, int quantity);
}

class FixedPriceStrategy implements ProductPricingStrategy {
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal("10.0");

    @Override
    public BigDecimal calculateProductPrice(UUID productId, int quantity) {
        return PRODUCT_PRICE.multiply(BigDecimal.valueOf(quantity));
    }
}

public class CustomPricingService implements PricingService {

    private final ProductPricingStrategy pricingStrategy;

    public CustomPricingService(ProductPricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public BigDecimal priceProducts(OrderPricingDTO orderPricingDTO) {
        Map<UUID, Integer> productMap = orderPricingDTO.productMap();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<UUID, Integer> entry : productMap.entrySet()) {
            UUID productId = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal productTotal = pricingStrategy.calculateProductPrice(productId, quantity);
            totalPrice = totalPrice.add(productTotal);
        }
        return totalPrice;
    }
}
