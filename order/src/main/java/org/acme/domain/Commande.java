package org.acme.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.acme.domain.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;



public class Commande {

    private CommandeId commandeId;
    private Products products;
    private LocalDateTime receivedAt = LocalDateTime.now(ZoneId.of("UTC"));
    private BigDecimal totalAmount = BigDecimal.ZERO;
    
    
    // #### Ordre attributes control
    private OrderStatus commandState;
    private Boolean paymentVerified; // payment microservice control
    private Boolean deliveryVerified; // delivery microservice control
    private Boolean pricingVerified; // pricing microservice control
    private Boolean stockVerified; // stock microservice control
   
   
    // Customer data-related attributes
   private Client clientInfo;


   @JsonCreator
   public static Commande of(CommandeId commandeId, Products products, LocalDateTime receivedAt,
                             BigDecimal totalAmount, OrderStatus commandState, Boolean paymentVerified,
                             Boolean deliveryVerified, Boolean pricingVerified, Boolean stockVerified,
                             Client clientInfo) {
       return new Commande(commandeId, products, receivedAt, totalAmount, commandState, paymentVerified,
                           deliveryVerified, pricingVerified, stockVerified, clientInfo);
   }

   private Commande(CommandeId commandeId, Products products, LocalDateTime receivedAt, BigDecimal totalAmount,
                    OrderStatus commandState, Boolean paymentVerified, Boolean deliveryVerified,
                    Boolean pricingVerified, Boolean stockVerified, Client clientInfo) {
       this.commandeId = commandeId;
       this.products = products;
       this.receivedAt = receivedAt;
       this.totalAmount = totalAmount;
       this.commandState = commandState;
       this.paymentVerified = false;
       this.deliveryVerified = false;
       this.pricingVerified = false;
       this.stockVerified = false;
       this.clientInfo = clientInfo;
   }

}
