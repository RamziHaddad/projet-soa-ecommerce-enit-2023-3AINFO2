package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

	@Override
	public List<Order> GetAllOrdersByClient(UUID idClient) {
		// TODO Auto-generated method stub
		return em
				.createQuery("SELECT o FROM Order o WHERE o.clientInfo.id = :clientId ORDER BY o.receivedAt",
						Order.class)
				.setParameter("clientId", idClient)
				.getResultList();
	}

	@Override
	public Optional<Order> queryOrderById(UUID idOrder) {
		// TODO Auto-generated method stub
		Order o = em.find(Order.class, idOrder);
		return Optional.ofNullable(o);
	}

}