package com.enit.shipping.applicationService;

import com.enit.shipping.api.DTOs.OrderRequest;

public interface ShippingService {
    void StartShipping(OrderRequest order);
    void shipped(Long idShipping);
}
