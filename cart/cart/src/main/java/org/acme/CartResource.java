package org.acme;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.transaction.Transactional;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartService cartService;

    @GET
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GET
    @Path("/{id}")
    public Cart getCart(@PathParam("id") Long id) {
        return cartService.getCart(id);
    }

    @POST
    @Transactional
    public void createCart(Cart cart) {
        cartService.createCart(cart);
    }

    @POST
    @Path("/{cartId}/items")
    @Transactional
    public void addItemToCart(@PathParam("cartId") Long cartId, CartItem cartItem) {
        cartService.addItemToCart(cartId, cartItem);
    }

    @DELETE
    @Path("/{cartId}/items/{itemId}")
    @Transactional
    public void removeItemFromCart(@PathParam("cartId") Long cartId, @PathParam("itemId") Long itemId) {
        cartService.removeItemFromCart(cartId, itemId);
    }

    @DELETE
    @Path("/{cartId}/items")
    @Transactional
    public void clearCart(@PathParam("cartId") Long cartId) {
        cartService.clearCart(cartId);
    }
}
