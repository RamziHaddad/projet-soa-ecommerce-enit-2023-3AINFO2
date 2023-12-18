package org.acme.Api.dto;

import java.util.UUID;

<<<<<<< HEAD
public record OrderDeliveryDto(UUID orderId , UUID idClient,  String address) {

=======
public record OrderDeliveryDto(UUID orderId, Map<UUID, Integer> productMap ,BigDecimal tatalAmount, String ville, String codePostal, String rue) {
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef
}
