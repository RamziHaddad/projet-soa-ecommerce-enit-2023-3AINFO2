package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.Discount;
import org.acme.domain.DiscountRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@ApplicationScoped
public class DiscountRepositoryImpl implements DiscountRepository {
    private List<Discount> data = new ArrayList<Discount>(Arrays.asList(
            new Discount(),
            new Discount()
    ));
    @Override
    public Discount getDiscount(UUID productID) {
        return null;
    }
}
