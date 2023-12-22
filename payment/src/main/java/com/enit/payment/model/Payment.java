package com.enit.payment.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "Payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "card_number")
    private int cardNumber;
    @Column(name = "secret_code")
    private int secretCode;





}
