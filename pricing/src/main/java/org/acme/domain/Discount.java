package org.acme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
public class Discount {
    @Id
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
