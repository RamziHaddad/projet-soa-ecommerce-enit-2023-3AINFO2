package org.acme.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.acme.domain.model.DeliveryNotification;
import org.acme.domain.model.PricingNotification;
import org.acme.domain.model.StockNotification;
import org.acme.domain.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;



public class Order {

    private OrderId commandeId;
    // items
    private Products products;
    // Order Date
    private LocalDateTime receivedAt = LocalDateTime.now(ZoneId.of("UTC"));
    
    private BigDecimal totalAmount = BigDecimal.ZERO;
    
    
    // #### Ordre attributes control
    private OrderStatus commandState;

    private PayementNotification paymentVerified; // payment microservice control
    private DeliveryNotification deliveryVerified; // delivery microservice control
    private PricingNotification pricingVerified; // pricing microservice control
    private StockNotification stockVerified; // stock microservice control
   
   
    // Customer data-related attributes
   private Client clientInfo;


   @JsonCreator
   public static Order of(OrderId commandeId, Products products, LocalDateTime receivedAt,
                             BigDecimal totalAmount, OrderStatus commandState,
                             PayementNotification paymentVerified, DeliveryNotification deliveryVerified,
                             PricingNotification pricingVerified, StockNotification stockVerified,
                             Client clientInfo) {
       return new Order(commandeId, products, receivedAt, totalAmount, commandState,
                           paymentVerified, deliveryVerified, pricingVerified, stockVerified, clientInfo);
   }

   private Order(OrderId commandeId, Products products, LocalDateTime receivedAt,
                    BigDecimal totalAmount, OrderStatus commandState,
                    PayementNotification paymentVerified, DeliveryNotification deliveryVerified,
                    PricingNotification pricingVerified, StockNotification stockVerified,
                    Client clientInfo) {
       this.commandeId = commandeId;
       this.products = products;
       this.receivedAt = receivedAt;
       this.totalAmount = totalAmount;
       this.commandState = OrderStatus.CREATED;

       this.paymentVerified = new PayementNotification();
       this.paymentVerified.setPayementNotificationState(false);

       this.deliveryVerified = new DeliveryNotification();
       this.deliveryVerified.setDeliveryNotificationState(false);

       this.pricingVerified = new PricingNotification();
       this.pricingVerified.setPricingNotificationState(false);

       this.stockVerified = new StockNotification();
       this.stockVerified.setStockNotificationState(false);

       this.clientInfo = clientInfo;
   }

}
