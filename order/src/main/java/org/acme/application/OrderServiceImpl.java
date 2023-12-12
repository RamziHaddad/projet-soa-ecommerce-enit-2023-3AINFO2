package org.acme.application;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.acme.Api.dto.OrderDeliveryDto;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.RequestFromPayementDTO;
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
import org.acme.domain.model.ClientAddress;
import org.acme.domain.model.DeliveryNotification;
import org.acme.domain.model.PricingNotification;
import org.acme.domain.model.StockNotification;
import org.acme.domain.model.enums.OrderStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class OrderServiceImpl implements  OrderService {

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
public void startPaymentRequest(Client clientInfo, OrderId orderId, BigDecimal totalAmount) {
    // Créer un objet OrderPaymentDTO avec les informations nécessaires
    OrderPayementDTO orderPaymentDTO = new OrderPayementDTO(orderId, totalAmount, clientInfo);
    
    // Appeler la méthode startPayment du service avec l'objet orderPaymentDTO
    paymentService.startPayment(orderPaymentDTO);
}



	@Override
	public void liberateItemsFromStock(OrderId orderid, Products products) {
		// TODO Auto-generated method stub
		envontoryservice.LiberateProducts(orderid, products);


	}



	@Override
	public void UpdateOrder(OrderId idOrder, Order ordre) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void DeleteOrder(OrderId Orderid) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void checkStock(OrderId orderid, Products products) {
		// TODO Auto-generated method stub
		envontoryservice.CheckProducts(orderid, products);
	}






	@Override
	public void checkPricing(OrderId orderid, Products products) {
		// TODO Auto-generated method stub
		
	     Pricingservice.CheckPricing(orderid, products);	
	}






	@Override
	public void sendNotificationEmailFailed(OrderId commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus) {
			orderstatus= false;
		// TODO Auto-generated method stub
		emailingservice.sendFailedMail(commandeId, totalAmount, recievedAT, orderstatus);

	}






	@Override
	public void sendNotificationEmailSuccess(OrderId commandeId, LocalDateTime recievedAT, BigDecimal totalAmount,
			boolean orderstatus) {
		// TODO Auto-generated method stub
		orderstatus= true;
		// TODO Auto-generated method stub
		emailingservice.sendSuccessMail(commandeId, totalAmount, recievedAT, orderstatus);
	}






	@Override
	public void StartDelivery(OrderId orderId, Products products, BigDecimal tatalAmount, ClientAddress clientAddress) {
		// TODO Auto-generated method stub
		DeliveryService.StartDelivery(orderId, products, tatalAmount, clientAddress);
	}





@Transactional
@Override
public void createOrder(Order order) {
    BigDecimal price = order.getTotalAmount(); // Supposant que getTotalAmount() retourne un BigDecimal

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
