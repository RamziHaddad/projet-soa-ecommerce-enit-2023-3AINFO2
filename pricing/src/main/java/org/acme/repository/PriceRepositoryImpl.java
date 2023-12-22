package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.PriceRepository;
import org.acme.domain.ProductPrice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@ApplicationScoped
public class PriceRepositoryImpl implements PriceRepository {
    private List<ProductPrice> data = new ArrayList<ProductPrice>(Arrays.asList(
            new ProductPrice(),
            new ProductPrice()
            ));
    @Override
    public ProductPrice getProductPrice(UUID productId) {
        return null;
    }
}
