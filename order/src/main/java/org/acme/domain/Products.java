package org.acme.domain;

import java.util.Map;
import java.util.UUID;

public record Products(Map<UUID, Integer> productMap) {
    
    public Products(Map.Entry<UUID, Integer> entry) {
        this(Map.of(entry.getKey(), entry.getValue()));
    }
}
