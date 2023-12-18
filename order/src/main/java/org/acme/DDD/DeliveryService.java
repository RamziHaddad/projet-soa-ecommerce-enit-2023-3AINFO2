package org.acme.DDD;

import org.acme.Api.dto.OrderDeliveryDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/shipping")
@RegisterRestClient(configKey="shipping-api")
public interface DeliveryService {
   
   @Path("/startshipping")
   public void StartDelivery(OrderDeliveryDto OrderDeliveryDto); 


}
