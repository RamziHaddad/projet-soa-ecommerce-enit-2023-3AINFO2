package org.acme.application;

import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.OrderViewDto;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/orders")
public class OrderController {

    @POST
    @Transactional
    public OrderViewDto addNewOrder(CreateOrderDto cmd){

        return new OrderViewDto();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}
