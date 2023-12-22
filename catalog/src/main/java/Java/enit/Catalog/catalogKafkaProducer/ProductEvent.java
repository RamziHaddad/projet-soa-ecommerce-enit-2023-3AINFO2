package Java.enit.Catalog.catalogKafkaProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import Java.enit.Catalog.catalogKafkaProducer.ProductEvent;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEvent
{
	 @Id
	   private Long product_id;
		private String description;
		private String message;
}
