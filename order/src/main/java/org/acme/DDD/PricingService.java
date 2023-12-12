package org.acme.DDD;

import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey="pricing-api")
public interface PricingService {
    
     @POST
     @Path("/Check-Pricing")
     void CheckPricing(OrderId orderId , Products products );

}
