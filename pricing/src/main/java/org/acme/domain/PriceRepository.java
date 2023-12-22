package org.acme.domain;

import java.math.BigDecimal;
import java.util.UUID;

public interface PriceRepository {
    ProductPrice getProductPrice(UUID productId);
}
