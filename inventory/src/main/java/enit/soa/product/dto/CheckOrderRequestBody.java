package enit.soa.product.dto;

import java.util.Map;
import java.util.UUID;

public class CheckOrderRequestBody {
    private UUID orderId;
    private Map<UUID, Integer> verifiedProducts;

    public CheckOrderRequestBody(UUID orderId, Map<UUID, Integer> verifiedProducts) {
        this.orderId = orderId;
        this.verifiedProducts = verifiedProducts;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Map<UUID, Integer> getProducts() {
        return verifiedProducts;
    }
}


