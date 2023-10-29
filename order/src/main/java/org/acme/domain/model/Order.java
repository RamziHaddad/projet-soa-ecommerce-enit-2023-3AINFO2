package org.acme.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import org.acme.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToMany(mappedBy="order",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();
    private String customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;
    private LocalDateTime receivedAt = LocalDateTime.now(ZoneId.of("UTC"));
    private BigDecimal price = BigDecimal.ZERO;

    public Order() {
    }
}
