package org.acme.domain;

import java.util.Optional;
import java.util.UUID;

public interface PriceRepository {
    Optional<ProductPrice> getProductPrice(UUID productId);
}
