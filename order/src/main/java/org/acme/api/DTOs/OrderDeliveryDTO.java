package org.acme.api.DTOs;

import java.util.UUID;

public record OrderDeliveryDTO(UUID orderId , UUID idClient,  String address) {

}