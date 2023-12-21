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

@ApplicationScoped
@Transactional
public class OrderRepositoryImpl implements OrderRepository{

	@Inject
	EntityManager em;

	@Override
	public void addOrder(Order order) {
		em.persist(order);
	}

	@Override
	public void updateOrder(OrderId OrderID, Order order) {
		em.merge(order);
	}

	@Override
	public Order getOrdrebyid(UUID id) {
		return em.find(Order.class, id);
	}

	@Override
	public void deleteOrder(OrderId orderId) {
		Order order = em.find(Order.class, orderId);
		if (order != null) {
			em.remove(order);
		}
	}

	@Override
	public List<Order> getAllOrdersByClient(UUID idClient) {
		return em
				.createQuery("SELECT o FROM Order o WHERE o.clientInfo.id = :clientId ORDER BY o.receivedAt",
						Order.class)
				.setParameter("clientId", idClient)
				.getResultList();
	}

	@Override
	public Optional<Order> queryOrderById(UUID idOrder) {
		Order o = em.find(Order.class, idOrder);
		return Optional.ofNullable(o);
	}

}