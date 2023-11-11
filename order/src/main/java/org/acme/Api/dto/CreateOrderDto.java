package org.acme.Api.dto;

import org.acme.domain.Client;
import org.acme.domain.OrderId;
import org.acme.domain.Products;
import java.math.BigDecimal;


public record CreateOrderDto(OrderId orderId, Products products ,BigDecimal tatalAmount, Client clientInfo ) {
} 