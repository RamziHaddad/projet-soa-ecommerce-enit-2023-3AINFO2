package enit.soa.product.dto;

import enit.soa.product.enums.ActionStatus;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductDTO {
    private String id;
    private Long totalQuantity;
    private Long requestedQuantity;
   
    // Default constructor
    public ProductDTO() {
    }

    // Constructor with all fields
    public ProductDTO(String id, Long totalQuantity, Long requestedQuantity) {
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

