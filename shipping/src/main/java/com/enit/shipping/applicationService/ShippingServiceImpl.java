package com.enit.shipping.applicationService;

import com.enit.shipping.api.DTOs.OrderRequest;
import com.enit.shipping.domain.Shipping;
import com.enit.shipping.domain.ShippingRepository;
import com.enit.shipping.domain.ShippingStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;
    public void StartShipping(OrderRequest order){
        Shipping shipping = Shipping.builder()
                .idOrder(order.getIdOrder())
                .idUser(order.getIdUser())
                .address(order.getAddress())
                .status(ShippingStatus.INTRANSIT)
                .build();
        shippingRepository.save(shipping);
    }
    public void shipped(Long idShipping){
        Shipping shipping = shippingRepository.findById(idShipping);
            shipping.setStatus(ShippingStatus.DELIVERED);
            shippingRepository.save(shipping);

    }
}
