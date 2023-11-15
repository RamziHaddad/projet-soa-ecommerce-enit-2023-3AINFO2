package org.acme.Api.dto;

import org.acme.domain.OrderId;
import org.acme.domain.Products;

public record OrderPrincingDTO(OrderId orderid,Products products) {
    
}
