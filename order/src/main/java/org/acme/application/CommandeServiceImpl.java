package org.acme.application;

import java.util.UUID;

import org.acme.Api.dto.CommandeEmailDTO;
import org.acme.Api.dto.CommandePayementDTO;
import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.RequestFromPayementDTO;
import org.acme.domain.Commande;
import org.acme.domain.CommandeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CommandeServiceImpl implements  CommandeService {

    @Inject
    CommandeRepository commandeRepository;

	

	@Override
	public void UpdateOrder(UUID idOrder, Commande ordre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Commande GetOrdrebyid(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void requestPayment(CommandePayementDTO CommandePayementINFO) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void sendNotificationEmail(CommandeEmailDTO CommandeEmailINFO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createOrder(CreateOrderDto createOrderDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePaymentStatus(UUID orderId, RequestFromPayementDTO RequestFromPayementDTO) {
		// TODO Auto-generated method stub
		
	}
    
}
