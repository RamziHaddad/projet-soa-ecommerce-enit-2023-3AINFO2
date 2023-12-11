package org.acme.DDD;

import java.math.BigDecimal;

import org.acme.Api.dto.OrderPayementDTO;
import org.acme.domain.Client;
import org.acme.domain.OrderId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


@RegisterRestClient(configKey = "payment-service")
public interface PayementService {
    
     @POST
     @Path("/startPayment")
     void startPayment(OrderPayementDTO orderpayementDTO);

}
