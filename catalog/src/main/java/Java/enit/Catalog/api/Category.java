package Java.enit.Catalog.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

import Java.enit.Catalog.catalogKafkaProducer.ProductEvent;
import Java.enit.Catalog.dto.CategoryRequest;
import Java.enit.Catalog.dto.CategoryResponse;
import Java.enit.Catalog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Api(tags = "Catalog API", value = "Catalog API")
@RequestMapping("/api/catalog")
public class Category {
 private final CategoryService catalogservice; 
 private final KafkaTemplate<String, ProductEvent> kafkaTemplate;


 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 @ApiOperation(value = "Créer un nouveau catalogue", response = Category.class)
 public Java.enit.Catalog.domain.Category createCategory(@RequestBody CategoryRequest catalogRequest) {
	 return catalogservice.createCategory(catalogRequest);
 }
 @GetMapping
 @ResponseStatus(HttpStatus.OK)
 @ApiOperation(value = "Récupérer tous les catalogues", response = CategoryResponse.class, responseContainer = "List")
 public List<CategoryResponse>getAllCategory(){
	return  catalogservice.getAllCategory();
 }
 @GetMapping("page")
 @ApiOperation(value = "Récupérer tous les catalogues avec pagination", response = CategoryResponse.class, responseContainer = "Page")

 public Page<CategoryResponse> getAllCategoryWithPagination(Pageable pageable){
	 return catalogservice.findAll(pageable);
 }
 @PostMapping("/kafka/publish")
 public ResponseEntity<String> envoyerMessage(@RequestBody ProductEvent product) {
     kafkaTemplate.send("catalog_topic", String.valueOf(product.getProduct_id()), product);
     return ResponseEntity.ok("Message envoyé à Kafka : " + product);
 }
}
