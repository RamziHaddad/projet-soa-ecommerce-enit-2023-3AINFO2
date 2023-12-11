package org.acme.infrastructure;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import org.acme.domain.Client;
import org.acme.domain.Order;
import org.acme.domain.OrderId;
import org.acme.domain.OrderRepository;




@ApplicationScoped
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

 @Inject
    EntityManager em;

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		em.persist(order);
	}


	@Override
	public void UpdateOrder(OrderId OrderID, Order order) {
		// TODO Auto-generated method stub
		em.merge(order);
	}


	@Override
	public Order GetOrdrebyid(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void DeleteOrder(OrderId Orderid) {
		// TODO Auto-generated method stub
		
	}


	

	





}



