package enit.soa.product.service;

import enit.soa.product.dto.ProductEventDTO;
import enit.soa.product.dto.ProductEventResponseDTO;
import enit.soa.product.entity.Product;
import enit.soa.product.enums.ActionStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class DefaultProductEventProcessingService implements ProductEventProcessingService {

    @Inject
    private ProductService productService;

    private static final Logger LOG = Logger.getLogger(DefaultProductEventProcessingService.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ProductEventResponseDTO processEvent(String idOrder, String productEventJson) {
        ProductEventDTO productEventDTO = fromJsonString(productEventJson);
        productEventDTO.setIdOrder(idOrder);
        Product product = productService.findById(productEventDTO.getIdProduct());

        if (ActionStatus.CONFIRM.equals(productEventDTO.getActionStatu())) {
            return processConfirmation(productEventDTO, product);
        } else if (ActionStatus.CANCEL.equals(productEventDTO.getActionStatu())) {
            return processCancellation(productEventDTO, product);
        } else {
            // Handle other cases or throw an exception for an unsupported action status
            return new ProductEventResponseDTO("Unsupported Action");
        }
    }

  

    private void logSuccess(String message, String string, Long long1) {
        LOG.log(Level.INFO, "{0}. Product: {1}, Remaining Quantity: {2}",
                new Object[]{message, string, long1});
    }

    private void logWarning(String message, String string, Long long1) {
        LOG.log(Level.WARNING, "{0}. Product: {1}, Requested Quantity: {2}",
                new Object[]{message, string, long1});
    }

    @Override
    public String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            LOG.warning("Error converting object to JSON string: " + e.getMessage());
            return "{}"; // or throw an exception, depending on your error handling strategy
        }
    }

    
}
