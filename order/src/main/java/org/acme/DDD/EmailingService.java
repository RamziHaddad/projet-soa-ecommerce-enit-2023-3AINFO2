package org.acme.DDD;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.acme.domain.OrderId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "Mailing-service")
public interface EmailingService {
    
    @POST
    @Path("/SendEmailSuccessNotif")
    void sendSuccessMail(OrderId CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT, boolean status);


     @POST
    @Path("/SendEmailFailedNotif")
    void sendFailedMail(OrderId CommandeId, BigDecimal TotalAmount, LocalDateTime RecievedAT, boolean status);



}
