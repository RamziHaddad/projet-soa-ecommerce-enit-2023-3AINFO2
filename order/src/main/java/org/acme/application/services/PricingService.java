package org.acme.application.services;

import java.math.BigDecimal;
import org.acme.api.DTOs.OrderPricingDTO;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "pricing-api")
public interface PricingService {

     @POST
     @Path("/Check-Pricing")
     BigDecimal CheckPricing(OrderPricingDTO OrderPrincingDTO);

}