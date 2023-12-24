package enit.soa.product.service;

import enit.soa.product.dto.ProductEventResponseDTO;

public interface ProductEventProcessingService {

    ProductEventResponseDTO processEvent(String idOrder, String productEventJson);

    String toJsonString(Object object);
}
