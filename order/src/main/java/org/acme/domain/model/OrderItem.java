package org.acme.domain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "OrdersItems")
@Data
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Order order;
    private int quantity;

    public OrderItem() {
    }


}
