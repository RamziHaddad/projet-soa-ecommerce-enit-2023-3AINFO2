package org.acme.infrastructure;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.UUID;

import org.acme.Api.dto.CommandeEmailDTO;
import org.acme.Api.dto.CommandePayementDTO;
import org.acme.domain.Commande;
import org.acme.domain.CommandeRepository;
import org.acme.domain.model.Order;

@ApplicationScoped
@Transactional
public class OrderRepository implements CommandeRepository {



	@Override
	public void createOrder(Commande ordre) {
		// TODO Auto-generated method stub
		
	}

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
	public void updatePaymentStatus(UUID orderId, Boolean paymentMade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotificationEmail(CommandeEmailDTO CommandeEmailINFO) {
		// TODO Auto-generated method stub
		
	}

    // On peut injecter un EntityManager au lieu de travailler avec ORM Panache,
    // mais cela nécessite une configuration manuelle.
}



