package org.acme.Api;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.OrderEmailDTO;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.OrderPrincingDTO;
import org.acme.Api.dto.OrderStockDTO;
import org.acme.application.OrderService;
import org.acme.domain.OrderId;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Path("/order")
public class OrderResource {
    

    @Inject
    OrderService orderService;

    // Step 1
    // create order 
    @POST
    @Transactional
    @Path("/create-order")
    public void createOrder(CreateOrderDto createOrderDto )
    {
        orderService.createOrder(createOrderDto.orderId(), createOrderDto.products(),createOrderDto.clientInfo(),createOrderDto.tatalAmount() );
    }

    // Step 2
    // check stock
    @POST
    @Transactional
    @Path("/create-order")
    public void checkStock(OrderStockDTO OrderStockDTO )
    {
        orderService.checkStock( OrderStockDTO.orderid(),OrderStockDTO.products());
    }


    // in case of availability of items

    // Step 3
    // check pricing
    
     @POST
    @Transactional
    @Path("/Check-pricing")
    public void Checkpricing(OrderPrincingDTO OrderPrincingDTO)
    {
        orderService.checkPricing(OrderPrincingDTO.orderid(), OrderPrincingDTO.products());
    }

    // Step 4
    // start payment process
    @POST
    @Transactional
    @Path("/Start-payement")
    public void requestPayment(OrderPayementDTO orderPaymentInfo)
    {
        orderService.startPaymentRequest(orderPaymentInfo.clintInfo(),orderPaymentInfo.OrderId(), orderPaymentInfo.TotalAmount() );
    }
      
    
    // In case of payment failure 
    // send notification mail of failed payment
    @POST
    @Transactional
    @Path("/NotificationMail-PaymentFailed")
    public void emailNotificationFailed(OrderEmailDTO orderEmailDTO)
    {
        
        
        orderService.sendNotificationEmailFailed(orderEmailDTO.CommandeId(), orderEmailDTO.RecievedAT(), orderEmailDTO.TotalAmount(),orderEmailDTO.Orderstatus());
    }

    // Compensate transaction by releasing products from stock
    @POST
    @Transactional
    @Path("/NotificationStock-LiberateItems")
    public void liberateItemsFromStock(OrderStockDTO orderStockDTO)
    {
        orderService.liberateItemsFromStock(orderStockDTO.orderid(),orderStockDTO.products());
    }

    // In case of successful payment 
    // send notification mail of successful payment 
    @POST
    @Transactional
    @Path("/NotificationMail-PaymentSucceed")
    public void emailNotificationSuccess(OrderEmailDTO orderEmailDTO)
    {
        orderService.sendNotificationEmailSuccess(orderEmailDTO.CommandeId(), orderEmailDTO.RecievedAT(), orderEmailDTO.TotalAmount(),orderEmailDTO.Orderstatus() );
    }

    // Start delivery

    @POST
    @Transactional
    @Path("/Start-Delivery")
    public void StartDelivery(OrderEmailDTO orderEmailDTO)
    {
        orderService.sendNotificationEmailSuccess(orderEmailDTO.CommandeId(), orderEmailDTO.RecievedAT(), orderEmailDTO.TotalAmount(),orderEmailDTO.Orderstatus() );
    }



// in case of  unavailability of items




}
