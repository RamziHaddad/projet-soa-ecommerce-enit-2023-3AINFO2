package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.domain.Discount;
import org.acme.domain.DiscountRepository;

import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@ApplicationScoped
@Transactional
public class DiscountRepositoryImpl implements DiscountRepository {
    @Inject
    EntityManager entityManager;
    @Override
    public Optional<Discount> getBestValidDiscount(UUID productID) {
        List<Discount> discounts =  entityManager.createQuery("SELECT d FROM Discount d WHERE d.productID=productID", Discount.class).getResultList();
        Discount bestDiscount = getBestDiscount(discounts);
        if (bestDiscount != null) return Optional.of(bestDiscount);
        return Optional.empty();
    }

    private Discount getBestDiscount(List<Discount> discounts) {
        Discount result = null;
        for (Discount discount : discounts) {
            if (result == null) result = discount;
            else if (discount.isValid() && discount.getDiscountFactor().compareTo(result.getDiscountFactor()) < 0) result = discount;
        }
        return result;
    }
}
