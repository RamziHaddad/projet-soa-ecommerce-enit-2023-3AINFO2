package org.acme.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.domain.model.OrderItem;
import org.acme.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderViewDto {
    private UUID id ;
    private String customer ;
    private List<OrderItem> items;
    private LocalDateTime receivedAt;
    private OrderStatus status;
    private BigDecimal price;

    public OrderViewDto() {

    }
}
