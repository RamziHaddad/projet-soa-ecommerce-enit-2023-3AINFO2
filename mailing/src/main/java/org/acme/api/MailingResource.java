package org.acme.api;

import org.acme.service.MailerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/mailing")
public class MailingResource {
    @Inject MailerService mailerService;

    @POST
    @Path("/ConfirmationPaiment")
    public void sendMailConfirmationPaiment(){
        this.mailerService.notifyConfirmationPaiment("slim", "slim.njah2@gmail.com");

    }
    @POST
    @Path("/ShippingOngoing")
    public void sendMailShippingOngoing(){
        this.mailerService.notifyShippingOngoing("slim", "slim.njah2@gmail.com");   
    }
}
