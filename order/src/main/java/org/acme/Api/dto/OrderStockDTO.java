package org.acme.Api.dto;

import org.acme.domain.OrderId;
import org.acme.domain.Products;

public record OrderStockDTO(OrderId orderid,Products products) {
    
}
