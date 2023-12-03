package enit.soa.product.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Product extends PanacheMongoEntity {
    private String id;
    private Long totalQuantity;
    private Long requestedQuantity;

    // Default constructor
    public Product() {
    }

    // Constructor with all fields
    public Product(String id, Long totalQuantity, Long requestedQuantity) {
        this.id = id;
        this.totalQuantity = totalQuantity;
        this.requestedQuantity = requestedQuantity;
    }

    // Getter and Setter methods for 'id'
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter methods for 'totalQuantity'
    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    // Getter and Setter methods for 'requestedQuantity'
    public Long getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Long requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}
