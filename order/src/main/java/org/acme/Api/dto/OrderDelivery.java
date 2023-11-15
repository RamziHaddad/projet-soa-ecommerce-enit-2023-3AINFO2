package org.acme.Api.dto;

import java.math.BigDecimal;


import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.acme.domain.model.ClientAddress;

public record OrderDelivery(OrderId orderId, Products products ,BigDecimal tatalAmount, ClientAddress clientAddress) {
    
}
