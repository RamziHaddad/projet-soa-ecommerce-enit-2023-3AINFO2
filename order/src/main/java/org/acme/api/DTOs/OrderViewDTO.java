package org.acme.api.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.acme.domain.Client;
import org.acme.domain.Order;
import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.acme.domain.enums.OrderStatus;
public record OrderViewDTO(OrderId orderId, Client clientInfo, Products products, LocalDateTime receivedAt,
                           OrderStatus status, BigDecimal totalAmount) {

    public OrderViewDTO(Order order) {
        this(order.getOrderId(), order.getClientInfo(), order.getProducts(), order.getReceivedAt(),
                order.getStatus(), order.getTotalAmount());
    }
}