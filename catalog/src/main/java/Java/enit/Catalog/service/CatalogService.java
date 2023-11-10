package Java.enit.Catalog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Java.enit.Catalog.domain.Catalog;
import Java.enit.Catalog.dto.CatalogRequest;
import Java.enit.Catalog.dto.CatalogResponse;
import Java.enit.Catalog.infrastructure.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor

public class CatalogService {
	 private final CatalogRepository catalogRepository;
	 
	    public Catalog createCatalog(CatalogRequest catalogRequest) {
	    	Catalog catalog=Catalog.builder()
	    			        .name(catalogRequest.getName())
	    			        .description(catalogRequest.getDescription())
	    			        .build();
	    	return catalogRepository.save(catalog);
	    }

	    public List<CatalogResponse> getAllCatalogs() {
	        List<Catalog>catalogs= catalogRepository.findAll();
	        return catalogs.stream().map(this::maptoCatalogResponse).toList();    
	        }
	    /**
	     * Get all the Catalog.
	     *
	     * @param pageable the pagination information.
	     * @return the list of Catalog.
	     */
            
	    public Page<CatalogResponse> findAll(Pageable pageable) {
	        return catalogRepository.findAll(pageable).map(this::maptoCatalogResponse);
	    }
	    public Optional<Catalog> getCatalogById(Long id) {
	        return catalogRepository.findById(id);
	    }

	    
	    public Catalog updateCatalog(Long id, Catalog updatedCatalog) {
	        Optional<Catalog> existingCatalog = catalogRepository.findById(id);
	        if (existingCatalog.isPresent()) {
	            Catalog catalog = existingCatalog.get();
	            catalog.setName(updatedCatalog.getName());
	            catalog.setDescription(updatedCatalog.getDescription());
	            return catalogRepository.save(catalog);
	        } else {
	            return null;
	        }
	    }

	    public void deleteCatalog(Long id) {
	        catalogRepository.deleteById(id);
	    }
	    
	    
      private CatalogResponse maptoCatalogResponse(Catalog catalog ) {
    	  return CatalogResponse.builder()
    			  .id(catalog.getId())
    			  .name(catalog.getName())
    			  .description(catalog.getDescription())
    			  .build();
    	  
      }
}
