package org.acme.DDD;

import org.acme.Api.dto.OrderStockDTO;

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
<<<<<<< HEAD
     Boolean CheckProducts( OrderStockDTO OrderStockDTO);
=======
     void CheckProducts(OrderStockDTO OrderStockDTO);
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef

}
