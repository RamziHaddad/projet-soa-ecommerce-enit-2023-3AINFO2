package org.acme.Api.dto;

import java.util.UUID;

public record OrderDeliveryDto(UUID orderId , UUID idClient,  String address) {

}
