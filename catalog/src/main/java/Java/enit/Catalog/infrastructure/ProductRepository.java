package Java.enit.Catalog.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import Java.enit.Catalog.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}  