package org.acme.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.acme.Api.dto.OrderDeliveryDto;
import org.acme.Api.dto.OrderEmailDTO;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.OrderPrincingDTO;
import org.acme.Api.dto.OrderStockDTO;
import org.acme.DDD.DeliveryService;
import org.acme.DDD.EmailingService;
import org.acme.DDD.PayementService;
import org.acme.DDD.PricingService;
import org.acme.DDD.StockService;
import org.acme.domain.Client;
import org.acme.domain.Order;
import org.acme.domain.OrderId;
import org.acme.domain.OrderRepository;
import org.acme.domain.PayementNotification;
import org.acme.domain.Products;
import org.acme.domain.model.DeliveryNotification;
import org.acme.domain.model.PricingNotification;
import org.acme.domain.model.StockNotification;
import org.acme.domain.model.enums.OrderStatus;
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
	PayementService paymentService;

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
		PayementNotification paymentNotification = new PayementNotification();
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
<<<<<<< HEAD
public void createOrder(OrderId orderId, Products products, Client clientInfo, BigDecimal totalAmount) {
    // Initialiser les objets de notification à false
    PayementNotification paymentNotification = new PayementNotification();
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
public void startPaymentRequest(Long cartNumber, Long secretCode, UUID orderId, BigDecimal totalAmount) {
    // Créer un objet OrderPaymentDTO avec les informations nécessaires
    OrderPayementDTO orderPaymentDTO = new OrderPayementDTO(orderId, totalAmount, secretCode, secretCode);
    
    // Appeler la méthode startPayment du service avec l'objet orderPaymentDTO
    paymentService.startPayment(orderPaymentDTO);

}

=======
	public void startPaymentRequest(Long cartNumber, Long secretCode, UUID orderId, BigDecimal totalAmount) {
		// Créer un objet OrderPaymentDTO avec les informations nécessaires
		OrderPayementDTO orderPaymentDTO = new OrderPayementDTO(orderId, totalAmount, secretCode, secretCode);
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef

		// Appeler la méthode startPayment du service avec l'objet orderPaymentDTO
		paymentService.startPayment(orderPaymentDTO);
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

<<<<<<< HEAD
		OrderStockDTO StockDTO = new OrderStockDTO(orderId,productMap);
		return envontoryservice.CheckProducts(StockDTO);
=======
		OrderStockDTO StockDTO = new OrderStockDTO(orderId, productMap);
		envontoryservice.CheckProducts(StockDTO);
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef
	}

	@Override
	public BigDecimal checkPricing(UUID orderid, Map<UUID, Integer> productMap) {
		// TODO Auto-generated method stub

		OrderPrincingDTO PricingDTO = new OrderPrincingDTO(orderid, productMap);

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
<<<<<<< HEAD
	public void StartDelivery(UUID orderId, UUID idClient, String address) {

		OrderDeliveryDto DeliveryDTO =new OrderDeliveryDto(orderId, idClient, address);
=======
	public void StartDelivery(UUID orderId, Map<UUID, Integer> productMap, BigDecimal tatalAmount, String codePostal,
			String rue, String ville) {

		OrderDeliveryDto DeliveryDTO = new OrderDeliveryDto(orderId, productMap, tatalAmount, ville, ville, ville);
>>>>>>> aaaaf3325e74ee5ce77ba37f89a8ba1c14bb0eef
		// TODO Auto-generated method stub
		DeliveryService.StartDelivery(DeliveryDTO);
	}

	@Transactional
	@Override
	public void createOrder(Order order) {
		// BigDecimal price = order.getTotalAmount(); // Vérification du microservice
		// pricing
		BigDecimal price = checkPricing(order.getCommandeId().id(), order.getProducts().getProductMap());

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

		return orderRepository.GetAllOrdersByClient(idClient);
	}

	@Transactional
	@Override
	public Optional<Order> getOrderById(UUID idOrder) {
		// TODO Auto-generated method stub
		return orderRepository.queryOrderById(idOrder);
	}

}
