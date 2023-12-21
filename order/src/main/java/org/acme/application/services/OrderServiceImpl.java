package org.acme.application.services; 

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
	StockService envontoryservice;;

	@Inject
	@RestClient
	PricingService Pricingservice;

	@Inject
	@RestClient
	DeliveryService DeliveryService;

	@Override
	public Order GetOrdrebyid(UUID id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		OrderStockDTO StockDTO = new OrderStockDTO(orderId, productMap);
		envontoryservice.LiberateProducts(StockDTO);

	}

	@Override
	public void UpdateOrder(OrderId idOrder, Order ordre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DeleteOrder(OrderId Orderid) {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public boolean checkStock(UUID orderId, Map<UUID, Integer> productMap) {
		// TODO Auto-generated method stub

		OrderStockDTO StockDTO = new OrderStockDTO(orderId,productMap);
		return envontoryservice.CheckProducts(StockDTO);
	}

	@Override
	public BigDecimal checkPricing(UUID orderid, Map<UUID, Integer> productMap) {
		// TODO Auto-generated method stub

		OrderPricingDTO PricingDTO = new OrderPricingDTO(orderid, productMap);

		return Pricingservice.CheckPricing(PricingDTO);
	}

	@Override
	public void sendNotificationEmailFailed(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus) {
		orderstatus = false;
		OrderEmailDTO EmailDTO = new OrderEmailDTO(commandeId, totalAmount, recievedAT, orderstatus);
		// TODO Auto-generated method stub
		emailingservice.sendFailedMail(EmailDTO);

	}

	@Override
	public void sendNotificationEmailSuccess(UUID commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus) {
		// TODO Auto-generated method stub
		orderstatus = true;
		OrderEmailDTO EmailDTO = new OrderEmailDTO(commandeId, totalAmount, recievedAT, orderstatus);
		// TODO Auto-generated method stub
		emailingservice.sendSuccessMail(EmailDTO);
	}

	@Override
	public void StartDelivery(UUID orderId, UUID idClient, String address) {

		OrderDeliveryDTO DeliveryDTO =new OrderDeliveryDTO(orderId, idClient, address);
		// TODO Auto-generated method stub
		DeliveryService.StartDelivery(DeliveryDTO);
	}

	@Transactional
	@Override
	public void createOrder(Order order) {
		// BigDecimal price = order.getTotalAmount(); // Vérification du microservice
		// pricing
		BigDecimal price = checkPricing(order.getOrderId().id(), order.getProducts().getProductMap());

		if (price == null) {
			throw new RuntimeException("couldn't determine price");
		} else {
			// Mettre à jour le montant total de la commande avec le prix
			order.setTotalAmount(price);

			// Mettre à jour l'état de vérification du pricing
			if (order.getPricingVerified() != null) {
				order.getPricingVerified().setPricingNotificationState(true);
			}

			// Mettre à jour le statut de la commande
			order.setStatus(OrderStatus.RECEIVED);
		}

		// Ajouter la commande à la base de données
		orderRepository.addOrder(order);
	}

	@Transactional
	@Override
	public List<Order> getAllOrdersByClient(UUID idClient) {
		// TODO Auto-generated method stub

		return orderRepository.getAllOrdersByClient(idClient);
	}

	@Transactional
	@Override
	public Optional<Order> getOrderById(UUID idOrder) {
		// TODO Auto-generated method stub
		return orderRepository.queryOrderById(idOrder);
	}

}