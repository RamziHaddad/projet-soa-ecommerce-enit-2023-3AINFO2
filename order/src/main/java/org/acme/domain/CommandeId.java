package org.acme.domain;

import java.util.UUID;

public record CommandeId(UUID id) {
    public CommandeId(){
        this(UUID.randomUUID());
    }

    public CommandeId(UUID id) {
        this.id = id;
    }

    
    
}
