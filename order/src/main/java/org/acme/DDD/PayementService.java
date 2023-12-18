package org.acme.DDD;

import org.acme.Api.dto.OrderPayementDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "payment-api")
public interface PayementService {

     @POST
     @Path("/startPayment")
     void startPayment(OrderPayementDTO orderpayementDTO);

}
