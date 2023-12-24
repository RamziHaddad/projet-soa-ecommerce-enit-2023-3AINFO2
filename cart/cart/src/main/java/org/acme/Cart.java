package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cart extends PanacheEntity {
    @OneToMany(cascade = CascadeType.ALL)
    public List<CartItem> cartItems; // List of items in the cart


    // Method to calculate the total price of the cart
    public double calculateTotalPrice() {
        return cartItems.stream().mapToDouble(CartItem::calculateTotalPrice).sum();
    }
}
