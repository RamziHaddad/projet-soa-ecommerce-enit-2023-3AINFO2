package org.acme.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.acme.domain.Order;
import org.acme.domain.OrderId;

public interface OrderRepository {

    void addOrder(Order order);

    void updateOrder(OrderId OrderID, Order order);

    Order getOrdrebyid(UUID id);

    void deleteOrder(OrderId Orderid);

    List<Order> getAllOrdersByClient(UUID idClient);

    Optional<Order> queryOrderById(UUID idOrder);

}