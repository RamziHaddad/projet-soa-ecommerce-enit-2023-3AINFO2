package org.acme.api;

import org.acme.service.MailerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/mailing")
public class MailingResource {
    @Inject MailerService mailerService;

    @POST
    @Path("/sendMail")
    public void sendMail(){
        System.out.println("done");
        this.mailerService.notifyConfirmationPaiment("slim", "slim.njah2@gmail.com");
    }
}
