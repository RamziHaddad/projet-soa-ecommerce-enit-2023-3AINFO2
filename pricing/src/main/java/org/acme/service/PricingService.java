
/*
package org.acme.service;

import org.acme.api.dtos.OrderPricingDTO;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;


public interface ProductPricingStrategy {
    BigDecimal calculateProductPrice(UUID productId, int quantity);
}

//simple fixed price strategy
class FixedPriceStrategy implements ProductPricingStrategy {
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal("10.0");

    @Override
    public BigDecimal calculateProductPrice(UUID productId, int quantity) {
        
        return PRODUCT_PRICE.multiply(BigDecimal.valueOf(quantity));
    }
}

//interface with customizable pricing strategy
public class CustomPricingService implements PricingService {

    private final ProductPricingStrategy pricingStrategy;

    // injecting the pricing strategy during instantiation
    public CustomPricingService(ProductPricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public BigDecimal priceProducts(OrderPricingDTO orderPricingDTO) {
        // Extract info from  DTO
        Map<UUID, Integer> productMap = orderPricingDTO.productMap();

        // total price based on provided pricing strategy
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<UUID, Integer> entry : productMap.entrySet()) {
            UUID productId = entry.getKey();
            int quantity = entry.getValue();
            // Use the pricing strategy to calculate the price for each product
            BigDecimal productTotal = pricingStrategy.calculateProductPrice(productId, quantity);
            totalPrice = totalPrice.add(productTotal);
        }

        return totalPrice;
    }
}
*/
