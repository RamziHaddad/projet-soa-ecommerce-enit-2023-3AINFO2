package org.acme.domain.Events;
import org.acme.domain.Description;
import org.acme.domain.ProductId;

public record ProductAddedInCatalog(ProductId productId, Description description) {
    
}
