package Java.enit.Catalog.catalogKafkaProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryKafkaProducer {
 
	private final KafkaTemplate<String, String> Template;
	private final String topic = "catalog_topic"; 
	
	public void sendMessage(String message) {
        Template.send(topic, message);
    }
}
