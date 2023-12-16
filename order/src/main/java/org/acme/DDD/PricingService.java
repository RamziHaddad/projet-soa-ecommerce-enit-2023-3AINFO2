     package org.acme.DDD;

     import java.math.BigDecimal;
     import org.acme.Api.dto.OrderPrincingDTO;

     import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

     import jakarta.ws.rs.POST;
     import jakarta.ws.rs.Path;

     @RegisterRestClient(configKey="pricing-api")
     public interface PricingService {
     
          @POST
          @Path("/Check-Pricing")
          BigDecimal CheckPricing(OrderPrincingDTO OrderPrincingDTO );

     }



