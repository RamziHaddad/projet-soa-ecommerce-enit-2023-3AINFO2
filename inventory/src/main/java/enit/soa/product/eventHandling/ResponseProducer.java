package enit.soa.product.eventHandling;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import enit.soa.product.dto.ProductEventResponseDTO;
import enit.soa.product.service.ProductEventProcessingService;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ResponseProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseProducer.class);

    @Inject
    private ProductEventProcessingService eventProcessingService;

    @Incoming("testTopic")
    @Outgoing("respTopic")
    @Timeout(500)
    @Retry(maxRetries = 3)
    @Fallback(fallbackMethod = "fallbackMethod")
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 10000)
    public KafkaRecord<String, String> forwardKafkaMessage(KafkaRecord<String, String> record) {
       
        LOG.info("Received message from testTopic - Key: {}, Value: {}", record.getKey(), record.getPayload());
       
        ProductEventResponseDTO responseDTO = eventProcessingService.processEvent(record.getKey(), record.getPayload());
        if (responseDTO != null) {
            LOG.info("Sending response to respTopic - Key: {}, Value: {}", record.getKey(), responseDTO.getMessage());
            return KafkaRecord.of(record.getKey(), responseDTO.getMessage());
        } else {
            LOG.warn("Received null responseDTO. No response will be sent to respTopic.");
            return null;
        }
    }

    // Fallback method
    public KafkaRecord<String, String> fallbackMethod(KafkaRecord<String, String> record) {
        LOG.warn("Fallback method called after attempt failed - Key: {}, Value: {}", record.getKey(), record.getPayload());
        return KafkaRecord.of(record.getKey(), "Fallback response");
    }
}
