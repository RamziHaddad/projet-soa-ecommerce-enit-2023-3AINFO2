package org.acme.application;

import java.util.UUID;

import org.acme.Api.dto.CommandeEmailDTO;
import org.acme.Api.dto.CommandePayementDTO;
import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.RequestFromPayementDTO;
import org.acme.domain.Commande;

public interface CommandeService {

    
    void createOrder(CreateOrderDto createOrderDto);
    void UpdateOrder(UUID idOrder, Commande ordre);
    Commande GetOrdrebyid(UUID id);
    void requestPayment(CommandePayementDTO CommandePayementINFO);
    void updatePaymentStatus(UUID orderId, RequestFromPayementDTO RequestFromPayementDTO);
    void sendNotificationEmail(CommandeEmailDTO CommandeEmailINFO);


}







