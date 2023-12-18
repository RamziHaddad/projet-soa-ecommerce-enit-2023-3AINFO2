package enit.soa.product.repository;

import java.util.List;

import org.bson.types.ObjectId;

import enit.soa.product.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {

    public Product findById(String id) {
        return find("_id", id).firstResult();
    }

    public List<Product> findAll() {
        return findAll();
    }

    public void create(Product product) {
        persist(product);
    }

    public void update(Product product) {
        update("totalQuantity = ?1, requestedQuantity = ?2 where id = ?3",
               product.getTotalQuantity(), product.getRequestedQuantity(), product.getId());
    }

    public void delete(String id) {
        delete("id", id);
    }
    
}