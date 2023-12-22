package org.acme.application.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import jakarta.persistence.EntityNotFoundException;

import org.acme.api.DTOs.OrderDeliveryDTO;
import org.acme.api.DTOs.OrderEmailDTO;
import org.acme.api.DTOs.OrderPaymentDTO;
import org.acme.api.DTOs.OrderPricingDTO;
import org.acme.api.DTOs.OrderStockDTO;
import org.acme.domain.Client;
import org.acme.domain.Order;
import org.acme.domain.OrderId;
import org.acme.domain.PaymentNotification;
import org.acme.domain.Products;
import org.acme.domain.DeliveryNotification;
import org.acme.domain.PricingNotification;
import org.acme.domain.StockNotification;
import org.acme.domain.enums.OrderStatus;
import org.acme.repository.OrderRepository;
import org.acme.exceptions.OrderNotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

	@Inject
	OrderRepository orderRepository;

	@Inject
	@RestClient
	PaymentService paymentService;

	@Inject
	@RestClient
	EmailingService emailingservice;

	@Inject
	@RestClient
	StockService stockService;

	@Inject
	@RestClient
	PricingService Pricingservice;

	@Inject
	@RestClient
	DeliveryService DeliveryService;

	@Override
	public Order getOrdrebyId(UUID id) throws EntityNotFoundException {
		Optional<Order> optionalOrder = orderRepository.queryOrderById(id);

		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		} else {
			throw new EntityNotFoundException("Order not found for ID: " + id);
		}
	}

	@Override
	public void createOrder(OrderId orderId, Products products, Client clientInfo, BigDecimal totalAmount) {
		// Initialiser les objets de notification à false
		PaymentNotification paymentNotification = new PaymentNotification();
		paymentNotification.setPayementNotificationState(false);

		DeliveryNotification deliveryNotification = new DeliveryNotification();
		deliveryNotification.setDeliveryNotificationState(false);

		PricingNotification pricingNotification = new PricingNotification();
		pricingNotification.setPricingNotificationState(false);

		StockNotification stockNotification = new StockNotification();
		stockNotification.setStockNotificationState(false);

		// Créer une nouvelle commande
		Order order = Order.of(orderId, products, LocalDateTime.now(ZoneId.of("UTC")), totalAmount, OrderStatus.CREATED,
				paymentNotification, deliveryNotification, pricingNotification, stockNotification, clientInfo);

		// Ajouter la commande au repository
		orderRepository.addOrder(order);
	}

	@Override
	public boolean startPaymentRequest(Long cartNumber, Long secretCode, UUID orderId, BigDecimal totalAmount) {
		// Créer un objet OrderPaymentDTO avec les informations nécessaires
		OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO(orderId, totalAmount, secretCode, secretCode);

		// Appeler la méthode startPayment du service avec l'objet orderPaymentDTO
		return paymentService.startPayment(orderPaymentDTO);

	}

	@Override
	public void liberateItemsFromStock(UUID orderId, Map<UUID, Integer> productMap) {
		OrderStockDTO StockDTO = new OrderStockDTO(orderId, productMap);
		stockService.LiberateProducts(StockDTO);
	}

	@Override
	public void updateOrder(UUID idOrder, Order ordre) throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.queryOrderById(idOrder);

		if (optionalOrder.isPresent()) {
			Order existingOrder = optionalOrder.get();

			// Update the fields of the existing order with the new order
			existingOrder.updateFrom(ordre);

			// Save the updated order
			orderRepository.addOrder(existingOrder);
		} else {
			throw new OrderNotFoundException("Order not found for ID: " + idOrder);
		}
	}

	@Transactional
	@Override
	public boolean checkStock(UUID orderId, Map<UUID, Integer> productMap) throws OrderNotFoundException {
		OrderStockDTO StockDTO = new OrderStockDTO(orderId, productMap);
		boolean res = stockService.CheckProducts(StockDTO);
		Order order = getOrdrebyId(orderId);
		if (res) {

			order.getStockVerified().setStockNotificationState(true);

		} else {
			order.getStockVerified().setStockNotificationState(true);
		}
		order.setOrderStatus(OrderStatus.PENDING);
		updateOrder(orderId, order);

		return res;
	}

	@Override
	public BigDecimal checkPricing(UUID orderId, Map<UUID, Integer> productMap) throws OrderNotFoundException {
		OrderPricingDTO pricingDTO = new OrderPricingDTO(orderId, productMap);
		BigDecimal pricingResult = Pricingservice.CheckPricing(pricingDTO);

		Order order = getOrdrebyId(orderId);
		if (pricingResult.compareTo(BigDecimal.ZERO) < 0) {
			order.getPricingVerified().setPricingNotificationState(false);
		} else {
			order.getPricingVerified().setPricingNotificationState(true);
		}

		order.setStatus(OrderStatus.PENDING);

		updateOrder(orderId, order);

		return pricingResult;
	}

	@Override
	public void sendNotificationEmailFailed(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus) {
		orderstatus = false;
		OrderEmailDTO EmailDTO = new OrderEmailDTO(commandeId, totalAmount, recievedAT, orderstatus);
		emailingservice.sendFailedMail(EmailDTO);
	}

	@Override
	public void sendNotificationEmailSuccess(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus) {
		orderstatus = true;
		OrderEmailDTO EmailDTO = new OrderEmailDTO(commandeId, totalAmount, recievedAT, orderstatus);
		emailingservice.sendSuccessMail(EmailDTO);
	}

	@Override
	public void startDelivery(UUID orderId, UUID idClient, String address) throws OrderNotFoundException {

		OrderDeliveryDTO DeliveryDTO = new OrderDeliveryDTO(orderId, idClient, address);
		DeliveryService.StartDelivery(DeliveryDTO);
		Order order = getOrdrebyId(orderId);
		order.getDeliveryVerified().setDeliveryNotificationState(true);
		order.setStatus(OrderStatus.FINISHED);
		updateOrder(orderId, order);
	}

	@Transactional
	@Override
	public void createOrder(Order order) {
		BigDecimal price;
		try {
			price = checkPricing(order.getOrderId().id(), order.getProducts().getProductMap());
			if (price == null) {
				throw new RuntimeException("couldn't determine price");
			} else {
				order.setTotalAmount(price);
				if (order.getPricingVerified() != null) {
					order.getPricingVerified().setPricingNotificationState(true);
				}
				order.setStatus(OrderStatus.RECEIVED);
			}
			orderRepository.addOrder(order);
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public List<Order> getAllOrdersByClient(UUID idClient) {
		return orderRepository.getAllOrdersByClient(idClient);
	}

	@Transactional
	@Override
	public Optional<Order> getOrderById(UUID idOrder) {
		return orderRepository.queryOrderById(idOrder);
	}

}