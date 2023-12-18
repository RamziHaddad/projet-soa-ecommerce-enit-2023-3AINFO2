package org.acme.Api;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.OrderDeliveryDto;
import org.acme.Api.dto.OrderEmailDTO;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.OrderPrincingDTO;
import org.acme.Api.dto.OrderStockDTO;
import org.acme.Api.dto.OrderViewDTO;
import org.acme.application.OrderService;
import org.acme.domain.Order;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Path("/orders")
public class OrderResource {
    

    @Inject
    OrderService orderService;



 // methode to return all orders d'un client
    @GET
    @Path("/ordersByClient/{id}")
    public List<OrderViewDTO> allOrderByClient(@PathParam("id") UUID idClient){
        List<Order> orders = orderService.getAllOrdersByClient(idClient);
        return orders.stream().map(OrderViewDTO::new).toList();
    }


    @GET
    @Path("/orderByid/{id}")
    public Response orderByid(@PathParam("id") UUID idOrder) {
    Optional<Order> optionalOrder = orderService.getOrderById(idOrder);

    if (optionalOrder.isPresent()) {
        OrderViewDTO orderViewDTO = new OrderViewDTO(optionalOrder.get());
        return Response.status(Response.Status.OK).entity(orderViewDTO).build();
    } else {
        return Response.status(Response.Status.NOT_FOUND).entity("Order not found for ID: " + idOrder).build();
    }
}


//  orchestration using SAGA pattern 


    // Step 1
    // create order 
    @POST
    @Transactional
    @Path("/create-order")
    public void createOrder(CreateOrderDto createOrderDto )
    {
        Order order = new Order(createOrderDto.orderId(), createOrderDto.products(),createOrderDto.clientInfo(),createOrderDto.tatalAmount());
       for (Map.Entry<UUID, Integer> entry : createOrderDto.products().getProductMap().entrySet()) {
        UUID productId = entry.getKey();
        Integer quantity = entry.getValue();
        // Appeler la méthode addItems pour chaque item
        order.addItem(productId, quantity);
    }
        orderService.createOrder(order); 
    }

    
    // Step 2
    // check stock
    @POST
    @Transactional
    @Path("/check-store")
    public Response checkStock(OrderStockDTO orderStockDTO) {
        boolean stockAvailable = orderService.checkStock(orderStockDTO.orderId(), orderStockDTO.productMap());
        return Response.ok().entity(stockAvailable).build();
    }


    // in case of availability of items

    // Step 3
    // check pricing
   
    
    @POST
    @Transactional
    @Path("/Check-pricing")
    public Response checkPricing(OrderPrincingDTO orderPrincingDTO) {
    // Assuming OrderPrincingDTO contains necessary information
    BigDecimal totalPrice = orderService.checkPricing(orderPrincingDTO.orderId(), 
                                                     orderPrincingDTO.productMap());
    
    // Additional logic based on the pricing check result can be added here
    
    return Response.ok().entity("Pricing check successful. Total Price: " + totalPrice).build();
}



    // Step 4
    // start payment process
    @POST
    @Transactional
    @Path("/Start-payement")
    public void requestPayment(OrderPayementDTO orderPaymentInfo)
    {
        orderService.startPaymentRequest(orderPaymentInfo.cartNumber(),orderPaymentInfo.secretCode(),orderPaymentInfo.OrderId(), orderPaymentInfo.TotalAmount() );
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
        orderService.liberateItemsFromStock(orderStockDTO.orderId(),orderStockDTO.productMap());
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
    public void startDelivery(OrderDeliveryDto orderDeliveryDto) {
        orderService.StartDelivery(orderDeliveryDto.orderId(), orderDeliveryDto.idClient(),
                orderDeliveryDto.address());
    }     


// in case of  unavailability of items




}
