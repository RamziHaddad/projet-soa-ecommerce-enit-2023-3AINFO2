package org.acme.application.services;

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
import org.acme.exceptions.OrderNotFoundException;

public interface OrderService {

        void createOrder(OrderId orderId, Products products, Client clientInfo, BigDecimal tatalAmount);

        void updateOrder(UUID idOrder, Order ordre) throws OrderNotFoundException;

        Order getOrdrebyId(UUID id);

        boolean startPaymentRequest(Long cartNumber, Long secretCode, UUID orderId, BigDecimal totalAmount);

        void liberateItemsFromStock(UUID orderId, Map<UUID, Integer> productMap);

        boolean checkStock(UUID orderId, Map<UUID, Integer> productMap) throws OrderNotFoundException;

        BigDecimal checkPricing(UUID orderid, Map<UUID, Integer> productMap) throws OrderNotFoundException;

        void sendNotificationEmailFailed(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
                        boolean orderstatus);

        void sendNotificationEmailSuccess(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
                        boolean orderstatus);

        void createOrder(Order order);

        List<Order> getAllOrdersByClient(UUID idClient);

        Optional<Order> getOrderById(UUID idOrder);

        void startDelivery(UUID orderId, UUID idClient, String address) throws OrderNotFoundException;

}