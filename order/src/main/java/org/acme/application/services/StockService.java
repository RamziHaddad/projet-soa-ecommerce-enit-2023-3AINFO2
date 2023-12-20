package org.acme.application.services;

import org.acme.api.DTOs.OrderStockDTO;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "inventory-api")
public interface StockService {

     @POST
     @Path("/LiberateProducts")
     void LiberateProducts(OrderStockDTO OrderStockDTO);

     @POST
     @Path("/CheckProducts")
     Boolean CheckProducts( OrderStockDTO OrderStockDTO);

}