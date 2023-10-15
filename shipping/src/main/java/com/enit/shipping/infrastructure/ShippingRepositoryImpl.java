package com.enit.shipping.infrastructure;

import com.enit.shipping.domain.Shipping;
import com.enit.shipping.domain.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingRepositoryImpl implements ShippingRepository {
    private final ShippingJpaRepository shippingJpaRepository;
    @Autowired
    public  ShippingRepositoryImpl(ShippingJpaRepository shiippingJpaRepository) {
        this.shippingJpaRepository = shiippingJpaRepository;
    }
    @Override
    public Shipping findById(Long idShipping) {
        return shippingJpaRepository.findById(idShipping).orElse(null);
    }

    @Override
    public void save(Shipping shipping) {
        shippingJpaRepository.save(shipping);
    }

    @Override
    public void delete(Shipping shipping) {
        shippingJpaRepository.delete(shipping);
    }
}
