package Java.enit.Catalog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Java.enit.Catalog.domain.Category;
import Java.enit.Catalog.dto.CategoryRequest;
import Java.enit.Catalog.dto.CategoryResponse;
import Java.enit.Catalog.infrastructure.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CategoryService {
	private final CategoryRepository catalogRepository;
	 
	    public Category createCategory(CategoryRequest catalogRequest) {
	    	Category catalog=Category.builder()
	    			        .name(catalogRequest.getName())
	    			        .description(catalogRequest.getDescription())
	    			        .build();
	    	return catalogRepository.save(catalog);
	    }

	    public List<CategoryResponse> getAllCategory() {
	        List<Category>catalogs= catalogRepository.findAll();
	        return catalogs.stream().map(this::maptoCatalogResponse).toList();    
	        }
	    /**
	     * Get all the Catalog.
	     *
	     * @param pageable the pagination information.
	     * @return the list of Catalog.
	     */
            
	    public Page<CategoryResponse> findAll(Pageable pageable) {
	        return catalogRepository.findAll(pageable).map(this::maptoCatalogResponse);
	    }
	    public Optional<Category> getCategoryById(Long id) {
	        return catalogRepository.findById(id);
	    }

	    
	    public Category updateCategory(Long id, Category updatedCatalog) {
	        Optional<Category> existingCatalog = catalogRepository.findById(id);
	        if (existingCatalog.isPresent()) {
	            Category catalog = existingCatalog.get();
	            catalog.setName(updatedCatalog.getName());
	            catalog.setDescription(updatedCatalog.getDescription());
	            return catalogRepository.save(catalog);
	        } else {
	            return null;
	        }
	    }

	    public void deleteCategory(Long id) {
	        catalogRepository.deleteById(id);
	    }
	    
	    
      private CategoryResponse maptoCatalogResponse(Category catalog ) {
    	  return CategoryResponse.builder()
    			  .id(catalog.getId())
    			  .name(catalog.getName())
    			  .description(catalog.getDescription())
    			  .build();
    	  
      }
}
