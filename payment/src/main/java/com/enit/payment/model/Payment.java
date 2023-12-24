package com.enit.payment.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

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
    private UUID id;
    @Column(name = "Amount")
    private BigDecimal amount;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "secret_code")
    private Long secretCode;





}
