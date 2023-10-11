package org.acme.api.DTOs;

import org.acme.domain.Description;
import org.acme.domain.ProductId;

public record AddProductDTO(ProductId productId, Description description) {
    
}
