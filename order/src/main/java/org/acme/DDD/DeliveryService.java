package org.acme.DDD;

import org.acme.Api.dto.OrderDeliveryDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

<<<<<<< HEAD
@Path("/shipping")
@RegisterRestClient(configKey="shipping-api")
public interface DeliveryService {
   
   @Path("/startshipping")
   public void StartDelivery(OrderDeliveryDto OrderDeliveryDto); 
=======
@Path("/Start-Delivery")
@RegisterRestClient(configKey = "shipping-api")
public interface DeliveryService {
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef

    @POST
    public void StartDelivery(OrderDeliveryDto OrderDeliveryDto);

}
