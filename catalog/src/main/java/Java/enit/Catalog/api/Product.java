package Java.enit.Catalog.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import Java.enit.Catalog.catalogKafkaProducer.ProductEvent;
import Java.enit.Catalog.dto.CategoryRequest;
import Java.enit.Catalog.dto.CategoryResponse;
import Java.enit.Catalog.dto.ProductRequest;
import Java.enit.Catalog.dto.ProductResponse;
import Java.enit.Catalog.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@AllArgsConstructor
@Api(tags = "Product API", value = "Product API")
@RequestMapping("/api/product")
public class Product {
 	private ProductService productService; 
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;
 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Créer un nouveau produit", response = Product.class)
 	public Java.enit.Catalog.domain.Product createProduct(@RequestBody ProductRequest productRequest) {
	Java.enit.Catalog.domain.Product newProduct= productService.createProduct(productRequest);
	ProductEvent productEvent = new ProductEvent();
    productEvent.setProduct_id(newProduct.getId());  
    kafkaTemplate.send("ProductAddedInCatalog", String.valueOf(productEvent.getProduct_id()), productEvent);
	return newProduct; 
	}
 @GetMapping
 @ResponseStatus(HttpStatus.OK)
 @ApiOperation(value = "Récupérer tous les produits", response = ProductResponse.class, responseContainer = "List")
 public List<ProductResponse>getAllProduct(){
	return  productService.getAllProduct();
 }
 @GetMapping("page")
 @ApiOperation(value = "Récupérer tous les produits avec pagination", response = ProductResponse.class, responseContainer = "Page")

 public Page<ProductResponse> getAllProductWithPagination(Pageable pageable){
	 return productService.findAll(pageable);
 }
 
}
