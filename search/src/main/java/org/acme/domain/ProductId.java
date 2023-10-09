package org.acme.domain;

import java.util.UUID;

public record ProductId(UUID id) {
    public ProductId(){
        this(UUID.randomUUID());
    }

    public ProductId(UUID id) {
        this.id = id;
    }

    
    
}
