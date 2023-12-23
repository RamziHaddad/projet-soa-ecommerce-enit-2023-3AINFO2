package org.acme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Entity
public class ProductPrice {
    @Id
    private UUID productID;
    private BigDecimal basePrice;

    public BigDecimal getDiscountedPrice(Discount discount) {
        if (discount.isValid() && discount.getDiscountedProductID().equals(this.productID)) {
            BigDecimal newPrice = basePrice.multiply(discount.getDiscountFactor());
            return newPrice;
        } else {
            return basePrice;
        }
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
