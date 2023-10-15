package com.enit.shipping.domain;

public interface ShippingRepository   {
    Shipping findById(Long idShipping);
    void save(Shipping shipping);
    void delete(Shipping shipping);
}
