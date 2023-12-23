package org.acme.domain;

import java.util.Optional;
import java.util.UUID;

public interface DiscountRepository {
    Optional<Discount> getBestValidDiscount(UUID productID);
}
