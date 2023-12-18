package enit.soa.product.service;

import enit.soa.product.entity.Product;
import enit.soa.product.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class.getName());

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.listAll();
    }

    @Override
    public void create(Product product) {
        productRepository.persist(product);
        logSuccess("Product created successfully", product.getId());
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
        logSuccess("Product updated successfully", product.getId());
    }

    @Override
    public void delete(String id) {
        Product product = findById(id);

        if (product != null) {
            productRepository.delete(product);
            logSuccess("Product deleted successfully", id);
        } else {
            logWarning("Product deletion failed. Product not found.", id);
        }
    }

    // Implement other methods based on your business logic

    private void logSuccess(String message, String productId) {
        LOG.log(Level.INFO, "{0}. Product: {1}", new Object[]{message, productId});
    }

    private void logWarning(String message, String productId) {
        LOG.log(Level.WARNING, "{0}. Product ID: {1}", new Object[]{message, productId});
    }
}
