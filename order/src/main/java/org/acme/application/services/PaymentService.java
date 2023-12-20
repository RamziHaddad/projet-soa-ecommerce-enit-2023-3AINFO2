package org.acme.application.services;
import org.acme.api.DTOs.OrderPaymentDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
@Path("/payment-Local")
@RegisterRestClient(configKey = "payment-api")
public interface PaymentService {
      
     @POST
     @Path("/startPayment")
     boolean startPayment(OrderPaymentDTO orderpayementDTO);

}