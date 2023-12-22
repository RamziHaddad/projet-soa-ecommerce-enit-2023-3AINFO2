package org.acme.domain;

import java.util.UUID;

public interface DiscountRepository {
    Discount getDiscount(UUID productID);
}
