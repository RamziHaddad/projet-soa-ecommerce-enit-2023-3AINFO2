package org.acme.DDD;

import org.acme.Api.dto.OrderDeliveryDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/Start-Delivery")
@RegisterRestClient(configKey="shipping-api")
public interface DeliveryService {
   
    @POST
   public void StartDelivery(OrderDeliveryDto OrderDeliveryDto); 


}
