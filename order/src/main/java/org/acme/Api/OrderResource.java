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
import org.acme.Api.dto.OrderPanierDTO;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.OrderPrincingDTO;
import org.acme.Api.dto.OrderStockDTO;
import org.acme.Api.dto.OrderViewDTO;
import org.acme.application.OrderService;
import org.acme.domain.Client;
import org.acme.domain.Order;
import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.acme.exceptions.EntityNotFoundException;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Path("/orders")
public class OrderResource {

    @Inject
    OrderService orderService;

    // methode to return all orders d'un client
    @GET
    @Path("/ordersByClient/{id}")
    public List<OrderViewDTO> allOrderByClient(@PathParam("id") UUID idClient) {
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

    // orchestration using SAGA pattern

    // Step 1
    // create order
 @POST
@Transactional
@Path("/create-order")
public Response createOrder(OrderPanierDTO orderPanierDTO) {
    try {
        OrderId orderId = new OrderId(orderPanierDTO.OrderId());
        Client clientInfo = new Client(
            orderPanierDTO.ClientId(),
            orderPanierDTO.secretCode(),
            orderPanierDTO.cartNumber(),
            orderPanierDTO.address()
        );
        Products products = new Products(orderPanierDTO.items());
        BigDecimal totalAmount = orderPanierDTO.Totalamount();

        Order order = new Order(orderId, products, clientInfo, totalAmount);

        for (Map.Entry<UUID, Integer> entry : orderPanierDTO.items().entrySet()) {
            UUID productId = entry.getKey();
            Integer quantity = entry.getValue();
            order.addItem(productId, quantity);
        }

        orderService.createOrder(order);

        return Response.ok().entity("Order created successfully.").build();
    } catch (Exception e) {
        // Gérer les erreurs d'une manière appropriée
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Failed to create order. Error: " + e.getMessage()).build();
    }
}


    // Step 2
    // check stock
    @POST
    @Transactional
    @Path("/check-store")
    public Response checkStock(OrderStockDTO orderStockDTO) throws EntityNotFoundException {
        boolean stockAvailable = orderService.checkStock(orderStockDTO.orderId(), orderStockDTO.productMap());
        return Response.ok().entity(stockAvailable).build();
    }

    // in case of availability of items

    // Step 3
    // check pricing
   
     // get ou post
    @POST
    @Transactional
    @Path("/Check-pricing")
    public Response checkPricing(OrderPrincingDTO orderPrincingDTO) throws EntityNotFoundException {
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
public Response requestPayment(OrderPayementDTO orderPaymentInfo) {
    boolean paymentResult = orderService.startPaymentRequest(orderPaymentInfo.cartNumber(),
                                                             orderPaymentInfo.secretCode(),
                                                             orderPaymentInfo.OrderId(),
                                                             orderPaymentInfo.TotalAmount());

    if (paymentResult) {
        return Response.ok().entity("Payment request successful.").build();
    } else {
        return Response.status(Response.Status.BAD_REQUEST).entity("Payment request failed.").build();
    }
}

    // In case of payment failure
    // send notification mail of failed payment
    @POST
    @Transactional
    @Path("/NotificationMail-PaymentFailed")
    public void emailNotificationFailed(OrderEmailDTO orderEmailDTO) {

        orderService.sendNotificationEmailFailed(orderEmailDTO.CommandeId(), orderEmailDTO.RecievedAT(),
                orderEmailDTO.TotalAmount(), orderEmailDTO.Orderstatus());
    }

    // Compensate transaction by releasing products from stock
    @POST
    @Transactional
    @Path("/NotificationStock-LiberateItems")
    public void liberateItemsFromStock(OrderStockDTO orderStockDTO) {
        orderService.liberateItemsFromStock(orderStockDTO.orderId(), orderStockDTO.productMap());
    }

    // In case of successful payment
    // send notification mail of successful payment
    @POST
    @Transactional
    @Path("/NotificationMail-PaymentSucceed")
    public void emailNotificationSuccess(OrderEmailDTO orderEmailDTO) {
        orderService.sendNotificationEmailSuccess(orderEmailDTO.CommandeId(), orderEmailDTO.RecievedAT(),
                orderEmailDTO.TotalAmount(), orderEmailDTO.Orderstatus());
    }

    // Start delivery

    @POST
    @Transactional
    @Path("/Start-Delivery")
    public void startDelivery(OrderDeliveryDto orderDeliveryDto) throws EntityNotFoundException {
        orderService.StartDelivery(orderDeliveryDto.orderId(), orderDeliveryDto.idClient(),
                orderDeliveryDto.address());
    }     


// in case of  unavailability of items



    // in case of unavailability of items

}
