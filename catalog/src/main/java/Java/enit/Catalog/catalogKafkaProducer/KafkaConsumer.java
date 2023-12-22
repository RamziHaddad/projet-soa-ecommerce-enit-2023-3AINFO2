package Java.enit.Catalog.catalogKafkaProducer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "catalog_topic", groupId = "group_id") 
    public void consumeMessage(ProductEvent product) {
        System.out.println("Message reçu : " + product);
    }
}
