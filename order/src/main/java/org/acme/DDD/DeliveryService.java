package org.acme.DDD;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.acme.domain.model.ClientAddress;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/Start-Delivery")
@RegisterRestClient(configKey="shipping-api")
public interface DeliveryService {
   
    @POST
   public void StartDelivery(OrderId orderId, Products products ,BigDecimal tatalAmount, ClientAddress clientAddress); // corriger dto


}
