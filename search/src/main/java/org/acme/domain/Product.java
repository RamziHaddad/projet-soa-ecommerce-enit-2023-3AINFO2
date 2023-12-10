package org.acme.domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

@Getter
public class Product{
    private ProductId productId;
    private Description description;

    @JsonCreator
    public static Product of(ProductId productId, Description description){
        return new Product(productId, description);
    }

    private Product(ProductId productId, Description description) {
        this.productId = productId;
        this.description = description;
    }
    
    
}
