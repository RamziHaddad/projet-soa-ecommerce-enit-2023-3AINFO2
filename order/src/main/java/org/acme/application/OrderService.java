package org.acme.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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

    
   
    void startPaymentRequest(Long cartNumber, Long secretCode, UUID orderId, BigDecimal totalAmount);
   
    void liberateItemsFromStock(UUID orderId, Map<UUID, Integer> productMap);

    boolean checkStock(UUID orderId, Map<UUID, Integer> productMap);

    BigDecimal checkPricing(UUID orderid, Map<UUID, Integer> productMap);
	void sendNotificationEmailFailed(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus);
	void sendNotificationEmailSuccess(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus);
    void createOrder(Order order); 
    List<Order> getAllOrdersByClient(UUID idClient);
    Optional<Order> getOrderById(UUID idOrder);
    
    
    void StartDelivery(UUID orderId, UUID idClient, String address);
    

}







