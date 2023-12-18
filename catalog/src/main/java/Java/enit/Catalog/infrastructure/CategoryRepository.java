package Java.enit.Catalog.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import Java.enit.Catalog.domain.Category;


public interface CategoryRepository extends JpaRepository<Category,Long>{
}
