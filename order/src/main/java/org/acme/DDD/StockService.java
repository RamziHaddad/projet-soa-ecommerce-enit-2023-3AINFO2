package org.acme.DDD;

import java.math.BigDecimal;

import org.acme.domain.Client;
import org.acme.domain.OrderId;
import org.acme.domain.Products;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

public interface StockService {
    

     
     @POST
     @Path("/LiberateProducts")
     void LiberateProducts( OrderId orderId, Products products);

}
