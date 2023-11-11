package org.acme.application;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.acme.Api.dto.RequestFromPayementDTO;
import org.acme.DDD.EmailingService;
import org.acme.DDD.PayementService;
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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class OrderServiceImpl implements  OrderService {

    @Inject
   OrderRepository orderRepository;

	@Inject
    PayementService paymentService;

	@Inject
    EmailingService emailingservice;

	@Inject
    StockService envontoryservice;;


   


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
	public void startPaymentRequest(Client clintInfo, OrderId orderId, BigDecimal totalAmount) {
		// TODO Auto-generated method stub
		paymentService.startPayment(clintInfo, orderId, totalAmount);
 
	}

	@Override
	public void sendNotificationEmailFailed(OrderId commandeId, LocalDateTime recievedAT, BigDecimal totalAmount) {
		// TODO Auto-generated method stub
		
       emailingservice.sendFailedMail(commandeId, totalAmount, recievedAT);

	}

	@Override
	public void liberateItemsFromStock(OrderId orderid, Products products) {
		// TODO Auto-generated method stub
		envontoryservice.LiberateProducts(orderid, products);


	}

	@Override
	public void sendNotificationEmailSuccess(OrderId commandeId, LocalDateTime recievedAT, BigDecimal totalAmount) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void UpdateOrder(OrderId idOrder, Order ordre) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void DeleteOrder(OrderId Orderid) {
		// TODO Auto-generated method stub
		
	}

	
    
}
