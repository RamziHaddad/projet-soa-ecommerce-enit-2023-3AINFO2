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

import Java.enit.Catalog.catalogKafkaProducer.CatalogKafkaProducer;
import Java.enit.Catalog.domain.Catalog;
import Java.enit.Catalog.dto.CatalogRequest;
import Java.enit.Catalog.dto.CatalogResponse;
import Java.enit.Catalog.service.CatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Api(tags = "Catalog API", value = "Catalog API")
@RequestMapping("/api/catalog")
public class Catalogapi {
 private final CatalogService catalogservice; 
 private final CatalogKafkaProducer kafkaProducer;

 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 @ApiOperation(value = "Créer un nouveau catalogue", response = Catalog.class)
 public Catalog createCatalog(@RequestBody CatalogRequest catalogRequest) {
	 return catalogservice.createCatalog(catalogRequest);
 }
 @GetMapping
 @ResponseStatus(HttpStatus.OK)
 @ApiOperation(value = "Récupérer tous les catalogues", response = CatalogResponse.class, responseContainer = "List")
 public List<CatalogResponse>getAllCatalog(){
	return  catalogservice.getAllCatalogs();
 }
 @GetMapping("page")
 @ApiOperation(value = "Récupérer tous les catalogues avec pagination", response = CatalogResponse.class, responseContainer = "Page")

 public Page<CatalogResponse> getAllCatalogWithPagination(Pageable pageable){
	 return catalogservice.findAll(pageable);
 }
 @PostMapping("/kafka/publish")
 public ResponseEntity<String> envoyerMessage(@RequestBody String message) {
     kafkaProducer.sendMessage(message);
     return ResponseEntity.ok("Message envoyé à Kafka : " + message);
 }
}
