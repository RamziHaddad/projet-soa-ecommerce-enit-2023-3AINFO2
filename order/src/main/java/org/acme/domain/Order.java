package org.acme.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.UUID;

import org.acme.domain.DeliveryNotification;
import org.acme.domain.PricingNotification;
import org.acme.domain.StockNotification;
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

    // items
    private Products products;

    // Order Date
    private LocalDateTime receivedAt = LocalDateTime.now(ZoneId.of("UTC"));

    private BigDecimal totalAmount = BigDecimal.ZERO;

    // Ordre attributes control
    @Enumerated(EnumType.STRING)
    private OrderStatus commandState;

    private PaymentNotification paymentVerified; // payment microservice control
    private DeliveryNotification deliveryVerified; // delivery microservice control
    private PricingNotification pricingVerified; // pricing microservice control
    private StockNotification stockVerified; // stock microservice control

    public OrderId getOrderId() {
        return orderId;
    }

    // Setter pour commandeId
    public void setOrderId(OrderId commandeId) {
        this.orderId = commandeId;
    }

    // Getter pour products
    public Products getProducts() {
        return products;
    }

    // Setter pour products
    public void setProducts(Products products) {
        this.products = products;
    }

    // Getter pour receivedAt
    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    // Setter pour receivedAt
    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    // Getter pour totalAmount
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    // Setter pour totalAmount
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter et Setter pour paymentVerified
    public PaymentNotification getPaymentVerified() {
        return paymentVerified;
    }

    public void setPaymentVerified(PaymentNotification paymentVerified) {
        this.paymentVerified = paymentVerified;
    }

    // Getter et Setter pour deliveryVerified
    public DeliveryNotification getDeliveryVerified() {
        return deliveryVerified;
    }

    public void setDeliveryVerified(DeliveryNotification deliveryVerified) {
        this.deliveryVerified = deliveryVerified;
    }

    // Getter et Setter pour pricingVerified
    public PricingNotification getPricingVerified() {
        return pricingVerified;
    }

    public void setPricingVerified(PricingNotification pricingVerified) {
        this.pricingVerified = pricingVerified;
    }

    // Getter et Setter pour stockVerified
    public StockNotification getStockVerified() {
        return stockVerified;
    }

    public void setStockVerified(StockNotification stockVerified) {
        this.stockVerified = stockVerified;
    }

    // Customer data-related attributes
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
        this.commandState = OrderStatus.CREATED;

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
        return commandState; // Utilisez la propriété commandState au lieu de la constante OrderStatus
    }

    public void setStatus(OrderStatus os) {
        commandState = os;
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

                // Vérifier si l'identifiant existe déjà dans la map des produits
                if (products.getProductMap().containsKey(identifiant)) {
                    // Si l'identifiant existe déjà, mettez à jour la quantité
                    products.getProductMap().put(identifiant, nouvelleQuantite);
                } else {
                    // Si l'identifiant n'existe pas, ajoutez-le à la map des produits
                    addItem(identifiant, nouvelleQuantite);
                }
            }
        }

    }

}