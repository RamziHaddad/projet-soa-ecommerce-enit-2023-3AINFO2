package org.acme.Api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

<<<<<<< HEAD

public record OrderEmailDTO(UUID CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT , boolean Orderstatus) 
{

=======
public record OrderEmailDTO(UUID CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT, boolean Orderstatus) {
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef
}
