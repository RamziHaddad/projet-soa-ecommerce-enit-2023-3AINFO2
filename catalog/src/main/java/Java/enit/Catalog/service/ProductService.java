package Java.enit.Catalog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Java.enit.Catalog.api.Category;
import Java.enit.Catalog.domain.Marque;
import Java.enit.Catalog.domain.Product;
import Java.enit.Catalog.dto.ProductRequest;
import Java.enit.Catalog.dto.ProductResponse;
import Java.enit.Catalog.infrastructure.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public Product createProduct(ProductRequest productRequest) {
	    	Product product=Product.builder()
	    			        .name(productRequest.getName())
                            .price(productRequest.getPrice())
	    			        .description(productRequest.getDescription())
	    			        .build();
	    	return productRepository.save(product);
	    }
    
    private ProductResponse maptoProductResponse(Product product ) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .build();
        
    }
    public List<ProductResponse> getAllProduct() {
	        List<Product>catalogs= productRepository.findAll();
	        return catalogs.stream().map(this::maptoProductResponse).toList();    
	        }
    public List<Product> getProductsByMarque(Marque marque) {
        return productRepository.findByMarque(marque);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

   
	public Page<ProductResponse> findAll(Pageable pageable) {
	        return productRepository.findAll(pageable).map(this::maptoProductResponse);
	    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
	    Optional<Product> existingProduct = productRepository.findById(id);
	    if (existingProduct.isPresent()) {
	        Product product = existingProduct.get();
	        product.setName(updatedProduct.getName());
	        product.setDescription(updatedProduct.getDescription());
	        return productRepository.save(product);
	        } else {
	            return null;
	        }
	    }
    
}
