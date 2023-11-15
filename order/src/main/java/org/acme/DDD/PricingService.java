package org.acme.DDD;

import org.acme.domain.OrderId;
import org.acme.domain.Products;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

public interface PricingService {
    
     @POST
     @Path("/Check-Pricing")
     void CheckPricing(OrderId orderId , Products products );

}
