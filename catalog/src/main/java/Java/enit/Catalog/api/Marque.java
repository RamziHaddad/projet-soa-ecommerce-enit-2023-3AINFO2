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
import lombok.AllArgsConstructor;
import Java.enit.Catalog.dto.CategoryRequest;
import Java.enit.Catalog.dto.CategoryResponse;
import Java.enit.Catalog.dto.MarqueRequest;
import Java.enit.Catalog.dto.MarqueResponse;
import Java.enit.Catalog.dto.ProductRequest;
import Java.enit.Catalog.dto.ProductResponse;
import Java.enit.Catalog.service.MarqueService;
import Java.enit.Catalog.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@AllArgsConstructor
@Api(tags = "Marque API", value = "Marque API")
@RequestMapping("/api/marque")
public class Marque {
 private MarqueService marqueService; 

 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 @ApiOperation(value = "Créer une nouvelle marque", response = Marque.class)
 public Java.enit.Catalog.domain.Marque createMarque(@RequestBody MarqueRequest marqueRequest) {
	return marqueService.createMarque(marqueRequest);
 }
 @GetMapping
 @ResponseStatus(HttpStatus.OK)
 @ApiOperation(value = "Récupérer tous les marques", response = MarqueResponse.class, responseContainer = "List")
 public List<MarqueResponse>getAllMarques(){
	return  marqueService.getAllMarques();
 }
 @GetMapping("page")
 @ApiOperation(value = "Récupérer tous les marques avec pagination", response = MarqueResponse.class, responseContainer = "Page")

 public Page<MarqueResponse> getAllMarqueWithPagination(Pageable pageable){
	 return marqueService.findAll(pageable);
 }
 
}
