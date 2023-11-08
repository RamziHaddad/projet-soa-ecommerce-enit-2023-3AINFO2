package org.acme.domain;

import java.util.UUID;

import org.acme.Api.dto.CommandeEmailDTO;
import org.acme.Api.dto.CommandePayementDTO;

public interface CommandeRepository {
    
  void createOrder(Commande ordre);
  void UpdateOrder(UUID idOrder, Commande ordre);
  Commande GetOrdrebyid(UUID id);

    void requestPayment(CommandePayementDTO CommandePayementINFO);
    void updatePaymentStatus(UUID orderId, Boolean paymentMade);
    void sendNotificationEmail(CommandeEmailDTO CommandeEmailINFO);

   
    // void sendDeliveryCompletionEmail(Ordre order);




}
