package Java.enit.Catalog.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import Java.enit.Catalog.domain.Catalog;


public interface CatalogRepository extends JpaRepository<Catalog,Long>{
}
