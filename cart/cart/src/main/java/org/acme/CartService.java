package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CartService {

    @Inject
    CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.listAll();
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void createCart(Cart cart) {
        cartRepository.persist(cart);
    }

    @Transactional
    public void addItemToCart(Long cartId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId);
        cart.cartItems.add(cartItem);
    }

    @Transactional
    public void removeItemFromCart(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId);
        cart.cartItems.removeIf(item -> item.id.equals(itemId));
    }

    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId);
        cart.cartItems.clear();
    }
}

