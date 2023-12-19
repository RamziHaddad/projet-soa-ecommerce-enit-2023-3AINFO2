package Java.enit.Catalog.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import Java.enit.Catalog.domain.Marque;


public interface MarqueRepository extends JpaRepository<Marque,Long>{
}
