package Java.enit.Catalog.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Java.enit.Catalog.domain.Category;
import Java.enit.Catalog.domain.Marque;
import Java.enit.Catalog.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findByMarque(Marque marque);
    List<Product> findByCategory(Java.enit.Catalog.api.Category category);
}  