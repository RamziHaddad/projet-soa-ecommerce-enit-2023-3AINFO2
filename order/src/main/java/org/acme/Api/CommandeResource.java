package org.acme.Api;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.RequestFromPayementDTO;

import java.util.UUID;

import org.acme.Api.dto.CommandeEmailDTO;
import org.acme.Api.dto.CommandePayementDTO;  
import org.acme.application.CommandeService;

import jakarta.inject.Inject;
@Path("/order")
public class CommandeResource {
    

    @Inject
    CommandeService commandeservice;


    @POST
    @Path("/create-order")
    public void createorder(CreateOrderDto CreateOrderDto )
    {
       commandeservice.createOrder(CreateOrderDto);
    }


    @POST
    @Path("/Request-payement")
    public void RequestPayement(CommandePayementDTO  CommandePayementINFO )
    {
       commandeservice.requestPayment(CommandePayementINFO);
    }


    @GET
    @Path("/GetFrom-Payement")
    public void ResposeFromPayement(UUID orderId, RequestFromPayementDTO RequestFromPayementDTO)
    {
       commandeservice.updatePaymentStatus(orderId, RequestFromPayementDTO);
    }


    @POST
    @Path("/Request-Email")
    public void RequestEmail(CommandeEmailDTO  CommandeEmailDTO)
    {
       commandeservice.sendNotificationEmail(CommandeEmailDTO);
    }

    



    





}
