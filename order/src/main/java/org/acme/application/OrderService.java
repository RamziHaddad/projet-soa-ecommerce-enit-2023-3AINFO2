package org.acme.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.acme.Api.dto.OrderEmailDTO;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.OrderStockDTO;
import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.OrderDeliveryDto;
import org.acme.Api.dto.RequestFromPayementDTO;
import org.acme.domain.Client;
import org.acme.domain.Order;
import org.acme.domain.OrderId;
import org.acme.domain.Products;
import org.acme.domain.model.ClientAddress;

public interface OrderService {

    
    void createOrder(OrderId orderId, Products products, Client clientInfo, BigDecimal tatalAmount);
    void UpdateOrder(OrderId idOrder, Order ordre);
    Order GetOrdrebyid(UUID id);
    void DeleteOrder(OrderId Orderid);

    
   
    void startPaymentRequest(Client clintInfo, OrderId orderId, BigDecimal totalAmount);
   
    void liberateItemsFromStock(OrderId orderid, Products products);
    void checkStock(OrderId orderid, Products products);
    void checkPricing(OrderId orderid, Products products);
	void sendNotificationEmailFailed(OrderId commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus);
	void sendNotificationEmailSuccess(OrderId commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus);
    void StartDelivery(OrderId orderId, Products products, BigDecimal tatalAmount, ClientAddress clientAddress);
    void createOrder(Order order);
    
    List<Order> getAllOrdersByClient(UUID idClient);
   

}







