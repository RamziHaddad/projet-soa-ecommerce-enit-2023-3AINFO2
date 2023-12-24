package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class CartItem extends PanacheEntity {
    public Long productId; // Identifier of the product in the cart
    public int quantity; // Quantity of the product in the cart
    public double productPrice; // Assuming a hypothetical product price field

    // Constructors
    public CartItem() {
        // Default constructor
    }

    public CartItem(Long productId, int quantity, double productPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        // Initialize other fields as needed
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // Method to calculate the total price of the cart item
    public double calculateTotalPrice() {
        return quantity * productPrice;
    }
}
