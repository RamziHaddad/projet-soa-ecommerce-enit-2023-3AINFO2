package org.acme.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.UUID;

import org.acme.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    private OrderId orderId;
    private Products products;
    private LocalDateTime receivedAt = LocalDateTime.now(ZoneId.of("UTC"));
    private BigDecimal totalAmount = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private PaymentNotification paymentVerified; 
    private DeliveryNotification deliveryVerified; 
    private PricingNotification pricingVerified; 
    private StockNotification stockVerified; 

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId commandeId) {
        this.orderId = commandeId;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentNotification getPaymentVerified() {
        return paymentVerified;
    }

    public void setPaymentVerified(PaymentNotification paymentVerified) {
        this.paymentVerified = paymentVerified;
    }

    public DeliveryNotification getDeliveryVerified() {
        return deliveryVerified;
    }

    public void setDeliveryVerified(DeliveryNotification deliveryVerified) {
        this.deliveryVerified = deliveryVerified;
    }

    public PricingNotification getPricingVerified() {
        return pricingVerified;
    }

    public void setPricingVerified(PricingNotification pricingVerified) {
        this.pricingVerified = pricingVerified;
    }

    public StockNotification getStockVerified() {
        return stockVerified;
    }

    public void setStockVerified(StockNotification stockVerified) {
        this.stockVerified = stockVerified;
    }

    private Client clientInfo;

    public Client getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(Client clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Order() {
    }

    @JsonCreator
    public static Order of(OrderId commandeId, Products products, LocalDateTime receivedAt,
            BigDecimal totalAmount, OrderStatus commandState,
            PaymentNotification paymentVerified, DeliveryNotification deliveryVerified,
            PricingNotification pricingVerified, StockNotification stockVerified,
            Client clientInfo) {
        return new Order(commandeId, products, clientInfo, totalAmount);
    }

    public Order(OrderId commandeId, Products products, Client clientInfo, BigDecimal totalAmount) {
        this.orderId = commandeId;
        this.products = products;
        this.clientInfo = clientInfo;
        this.totalAmount = totalAmount;
        this.orderStatus = OrderStatus.CREATED;
        this.paymentVerified = new PaymentNotification();
        this.paymentVerified.setPayementNotificationState(false);
        this.deliveryVerified = new DeliveryNotification();
        this.deliveryVerified.setDeliveryNotificationState(false);
        this.pricingVerified = new PricingNotification();
        this.pricingVerified.setPricingNotificationState(false);
        this.stockVerified = new StockNotification();
        this.stockVerified.setStockNotificationState(false);
    }

    public OrderStatus getStatus() {
        return orderStatus; 
    }

    public void setStatus(OrderStatus os) {
        orderStatus = os;
    }

    public void addItem(UUID identifiant, Integer quantite) {
        if (products == null) {
            products = new Products(Map.of(identifiant, quantite));
        } else {
            products.getProductMap().put(identifiant, quantite);
        }

    }

    public void addItems(Map<UUID, Integer> items) {
        if (products == null) {
            products = new Products(items);
        } else {
            for (Map.Entry<UUID, Integer> entry : items.entrySet()) {
                UUID identifiant = entry.getKey();
                Integer nouvelleQuantite = entry.getValue();

                if (products.getProductMap().containsKey(identifiant)) {
                    products.getProductMap().put(identifiant, nouvelleQuantite);
                } else {
                    addItem(identifiant, nouvelleQuantite);
                }
            }
        }

    }

    public void updateFrom(Order newOrder) {
        this.setOrderId(newOrder.getOrderId());
        this.setProducts(newOrder.getProducts());
        this.setReceivedAt(newOrder.getReceivedAt());
        this.setTotalAmount(newOrder.getTotalAmount());
        this.setOrderStatus(newOrder.getOrderStatus());
        this.setPaymentVerified(newOrder.getPaymentVerified());
        this.setDeliveryVerified(newOrder.getDeliveryVerified());
        this.setPricingVerified(newOrder.getPricingVerified());
        this.setStockVerified(newOrder.getStockVerified());
    }

}