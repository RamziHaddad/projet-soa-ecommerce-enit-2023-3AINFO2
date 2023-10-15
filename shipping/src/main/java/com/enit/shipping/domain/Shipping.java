package com.enit.shipping.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "shipping")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long idShipping;
    @Column(nullable = false)
    private Long idOrder;
    @Column(nullable = false)
    private Long idUser;
    @Column(nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

}
