package enit.rhaddad.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import enit.rhaddad.domain.Payment;
import enit.rhaddad.service.DuplicatePaymentException;
import enit.rhaddad.service.PaymentService;

@Path("/payment")
public class PaymentResource {
    

    @ConfigProperty(name = "bank.payment.latency-ms")
    long latency=0;
    @ConfigProperty(name = "bank.payment.error-probability")
    short errorProbability=0;

    @Inject
    PaymentService paymentService;

    @POST
    @Transactional
    public Response makeNewPayment(MakePaymentCommand cmd){
        if(latency>0){
            try {
                Thread.sleep(latency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double random = Math.random()*100;
        if(random<errorProbability){
            throw new RuntimeException();
        } 
        if(cmd.cardNumber()==cmd.cardCode()){
            Payment p = new Payment(cmd.transactionId(), cmd.amount());
            try {
                paymentService.makeNewPayment(p);
                return Response.status(Response.Status.OK).build();
            } catch (DuplicatePaymentException e) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @GET
    public List<Payment> allPayment(){
        return paymentService.getAllPayments();
    }

    @GET
    @Path("/{id}")
    public Response paymentById(@PathParam("id") UUID id){
        Optional<Payment> o = paymentService.getPaymentById(id);
        if(o.isPresent()){
            return Response.status(Response.Status.OK).entity(o.get()).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity(id).build();
        }
    }
}
