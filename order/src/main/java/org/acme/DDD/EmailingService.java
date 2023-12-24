package org.acme.DDD;

import org.acme.Api.dto.OrderEmailDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "mail-api")
public interface EmailingService {

    @POST
    @Path("/SendEmailSuccessNotif")
    void sendSuccessMail(OrderEmailDTO OrderEmailDTO);

    @POST
    @Path("/SendEmailFailedNotif")
    void sendFailedMail(OrderEmailDTO OrderEmailDTO);

}
