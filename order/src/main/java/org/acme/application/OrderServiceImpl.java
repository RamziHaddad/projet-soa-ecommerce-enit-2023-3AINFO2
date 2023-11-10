package org.acme.application;

import java.util.UUID;

import org.acme.Api.dto.OrderEmailDTO;
import org.acme.Api.dto.OrderPayementDTO;
import org.acme.Api.dto.OrderStockDTO;
import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.RequestFromPayementDTO;
import org.acme.domain.Order;
import org.acme.infrastructure.OrderRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderServiceImpl implements  OrderService {

    @Inject
   OrderRepository orderRepository;

	@Override
	public void createOrder(CreateOrderDto createOrderDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateOrder(UUID idOrder, Order ordre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order GetOrdrebyid(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePaymentStatus(UUID orderId, RequestFromPayementDTO RequestFromPayementDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPaymentRequest(OrderPayementDTO commandePaymentInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotificationEmailFailed(OrderEmailDTO commandeEmailDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void liberateItemsFromStock(OrderStockDTO commandeStockDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotificationEmailSuccess(OrderEmailDTO commandeEmailDTO) {
		// TODO Auto-generated method stub
		
	}

	
    
}
