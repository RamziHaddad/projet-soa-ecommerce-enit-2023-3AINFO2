package org.acme.domain;

import java.util.UUID;

public record OrderId(UUID id) {
    public OrderId(){
        this(UUID.randomUUID());
    }

    public OrderId(UUID id) {
        this.id = id;
    }

    
    
}
