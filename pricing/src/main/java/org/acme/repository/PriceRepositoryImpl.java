package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.domain.Discount;
import org.acme.domain.PriceRepository;
import org.acme.domain.ProductPrice;

import java.util.Optional;
import java.util.UUID;
@ApplicationScoped
@Transactional
public class PriceRepositoryImpl implements PriceRepository {
    @Inject
    EntityManager entityManager;
    @Override
    public Optional<ProductPrice> getProductPrice(UUID productId) {
        return Optional.ofNullable(entityManager.find(ProductPrice.class, productId));
    }
}
