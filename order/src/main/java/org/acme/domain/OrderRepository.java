package org.acme.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    void addOrder(Order order);
    void UpdateOrder(OrderId OrderID, Order order);
    Order GetOrdrebyid(UUID id);
    void DeleteOrder(OrderId Orderid);
    List<Order> GetAllOrdersByClient(UUID idClient);
    Optional<Order> queryOrderById(UUID idOrder);


  
   

}
