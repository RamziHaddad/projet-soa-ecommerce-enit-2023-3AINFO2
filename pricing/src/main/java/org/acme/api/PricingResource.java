package org.acme.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.api.dtos.OrderPricingDTO;
import org.acme.service.PricingService;

import java.math.BigDecimal;

@Path("/pricing-api")
public class PricingResource {
    @Inject
    PricingService pricingService;

    @GET
    public BigDecimal checkPricing(OrderPricingDTO orderPricingDTO) {
        return pricingService.priceProducts(orderPricingDTO.productMap());
    }

}
