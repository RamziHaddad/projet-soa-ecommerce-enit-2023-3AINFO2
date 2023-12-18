package enit.soa.product.service;

import enit.soa.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product findById(String id);

    List<Product> findAll();

    void create(Product product);

    void update(Product product);

    void delete(String id);

    // Add other business-specific methods as needed

}
