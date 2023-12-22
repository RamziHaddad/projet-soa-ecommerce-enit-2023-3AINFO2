package org.acme.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Discount {
    private UUID discountID;
    private UUID productID;
    private BigDecimal discountFactor;
    private LocalDate firstValidDate;
    private LocalDate lastValidDate;

    public boolean isValid() {
        LocalDate today = LocalDate.now();
        return (today.isAfter(firstValidDate) && today.isBefore(lastValidDate));
    }

    public UUID getDiscountedProductID() {
        return this.productID;
    }
    public BigDecimal getDiscountFactor() {
        return this.discountFactor;
    }
}
