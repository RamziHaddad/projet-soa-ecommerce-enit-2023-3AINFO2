package org.acme.application.services;

import org.acme.api.DTOs.OrderDeliveryDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.Path;

@Path("/shipping")
@RegisterRestClient(configKey="shipping-api")
public interface DeliveryService {
   
   @Path("/startshipping")
   public void StartDelivery(OrderDeliveryDTO OrderDeliveryDto); 


}