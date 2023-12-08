package org.acme.DDD;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.acme.domain.model.ClientAddress;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

public interface DeliveryService {
    @POST
    @Path("/Start-Delivery")
    void StartDelivery(OrderId orderId, Products products ,BigDecimal tatalAmount, ClientAddress clientAddress);


}
