package enit.soa.product.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import enit.soa.product.dto.CheckOrderRequestBody;
import enit.soa.product.dto.CheckStockResponse;
import enit.soa.product.dto.ProductDTO;
import enit.soa.product.dto.checkStockResponse;
import enit.soa.product.mapper.ProductMapper;
import enit.soa.product.service.ProductService;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("stock")
public class ProductController {

    @Inject
    ProductService productService;
    @Inject
    ProductMapper productMapper;
    private static final int MAX_RETRIES = 3;
    private int currentRetry = 0;
    private static final long RETRY_DELAY_MILLIS = 100; // 100 milliseconds delay between retries
    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @GET
    @Path("product/{id}")
    @Timeout(1000)
    @Fallback(fallbackMethod = "fallbackMethod")
    @Retry(maxRetries = 3) // Set the maximum number of retries
    public Response get(@PathParam("id") String id) {
        try {
            ProductDTO product = productMapper.toDto(productService.findById(id));
            if (product != null) {
                logger.log(Level.INFO, "Product found: {0}", product);
                // Reset retry count on success
                currentRetry = 0;
                return Response.ok(product).build();
            } else {
                logger.log(Level.WARNING, "Product not found with id: {0}", id);
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "Entity not found error getting product", e);
            return Response.status(Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting product", e);
            // Increment retry count
            currentRetry++;
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error: Max attempt of api call reached")
                    .build();
        }
    }

    // Fallback method
    public Response fallbackMethod(String id) {
        logger.log(Level.WARNING, "Fallback method called after attempt {0} failed", currentRetry);
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Fallback after attempt " + currentRetry + " failed")
                .build();
    }

    // @GET
    // public Response getAll() {
    // try {
    // List<ProductDTO> products = productService.findAll();
    // logger.log(Level.INFO, "Retrieved all products: {0}", products);
    // return Response.ok(products).build();
    // } catch (Exception e) {
    // logger.log(Level.SEVERE, "Error getting all products", e);
    // return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    // }
    // }

    @POST
    @Path("product/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timeout(1000)
    @Retry(maxRetries = 3) // Set the maximum number of retries
    @Fallback(fallbackMethod = "fallbackMethodCreate")
    public Response create(ProductDTO productDTO) throws URISyntaxException {
        try {
            productService.create(productMapper.toEntity(productDTO));
            logger.log(Level.INFO, "Product created: {0}", productDTO);
            return Response.created(new URI("/" + productDTO.getId())).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating product", e);
            currentRetry++;
            if (currentRetry < MAX_RETRIES) {
                long delayMillis = (long) (Math.pow(2, currentRetry) * RETRY_DELAY_MILLIS);
                logger.log(Level.WARNING, "Retrying... Attempt {0} of {1}",
                        new Object[] { currentRetry, MAX_RETRIES });
                // Introduce a delay between retries
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        logger.warning("All attempts failed!!!!");
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Max attempt of API call reached")
                .build();
    }

    public Response fallbackMethodCreate(ProductDTO productDTO) {
        logger.log(Level.WARNING, "Fallback method called for create after attempt failed");
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Fallback after all attempts failed for create")
                .build();
    }

    @PUT
    @Path("product/{id}")
    @Timeout(1000)
    @Retry(maxRetries = 3) // Set the maximum number of retries
    @Fallback(fallbackMethod = "fallbackMethodUpdate")
    public Response update(@PathParam("id") String id, ProductDTO productDTO) {
        try {
            productDTO.setId(id);
            productService.update(productMapper.toEntity(productDTO));
            logger.log(Level.INFO, "Product updated: {0}", productDTO);
            return Response.ok(productDTO).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating product", e);
            currentRetry++;
            if (currentRetry < MAX_RETRIES) {
                long delayMillis = (long) (Math.pow(2, currentRetry) * RETRY_DELAY_MILLIS);
                logger.log(Level.WARNING, "Retrying... Attempt {0} of {1}",
                        new Object[] { currentRetry, MAX_RETRIES });
                // Introduce a delay between retries
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        logger.warning("All attempts failed!!!!");
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Max attempt of API call reached")
                .build();
    }

    public Response fallbackMethodUpdate(@PathParam("id") String id, ProductDTO productDTO) {
        logger.log(Level.WARNING, "Fallback method called for update after attempt failed");
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Fallback after all attempts failed for update")
                .build();
    }

    @DELETE
    @Path("product/{id}")
    @Timeout(1000)
    @Retry(maxRetries = 3) // Set the maximum number of retries
    @Fallback(fallbackMethod = "fallbackMethodDelete")
    public Response delete(@PathParam("id") String id) {
        try {
            productService.delete(id);
            logger.log(Level.INFO, "Product deleted with id: {0}", id);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting product", e);
            currentRetry++;
            if (currentRetry < MAX_RETRIES) {
                long delayMillis = (long) (Math.pow(2, currentRetry) * RETRY_DELAY_MILLIS);
                logger.log(Level.WARNING, "Retrying... Attempt {0} of {1}",
                        new Object[] { currentRetry, MAX_RETRIES });
                // Introduce a delay between retries
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        logger.warning("All attempts failed!!!!");
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Max attempt of API call reached")
                .build();
    }

    public Response fallbackMethodDelete(@PathParam("id") String id) {
        logger.log(Level.WARNING, "Fallback method called for delete after attempt failed");
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error: Fallback after all attempts failed for delete")
                .build();
    }

    @POST
    @Path("/checkProducts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timeout(1000)
    @Retry(maxRetries = 3)
    public boolean create(CheckOrderRequestBody checkOrderRequestBody) {
        try {
            // Iterate through the productMap
            for (Map.Entry<UUID, Integer> entry : checkOrderRequestBody.getProducts().entrySet()) {
                UUID productId = entry.getKey();
                Integer requestedQuantity = entry.getValue();

                // Fetch product details
                ProductDTO product = productMapper.toDto(productService.findById(productId.toString()));

                if (product.getTotalQuantity() == null || product.getTotalQuantity() < requestedQuantity) {
                    // Product verification failed
                    // Log the failure
                    logger.log(Level.INFO,
                            "Product verification failed for orderId={0}, productId={1}, requestedQuantity={2}",
                            new Object[] { checkOrderRequestBody.getOrderId(), productId, requestedQuantity });

                    return false; // Return false if any product verification fails
                }

                // Log product verification success
                logger.log(Level.INFO,
                        "Product verification successful for orderId={0}, productId={1}, requestedQuantity={2}",
                        new Object[] { checkOrderRequestBody.getOrderId(), productId, requestedQuantity });
            }

            // Log the creation of the response
            logger.log(Level.INFO, "All products verified successfully for orderId={0}",
                    checkOrderRequestBody.getOrderId());

            return true; // Return true if all product verifications are successful
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error in create method for orderId=" + checkOrderRequestBody.getOrderId(), e);

            // Return false in case of an exception or handle the error case accordingly
            return false;
        }
    }

    @POST
    @Path("/order/{orderId}/liberateProducts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timeout(1000)
    @Retry(maxRetries = 3)
    public void liberateProducts(CheckOrderRequestBody checkOrderRequestBody) throws Exception {
        try {
            // Iterate through the productMap
            for (Map.Entry<UUID, Integer> entry : checkOrderRequestBody.getProducts().entrySet()) {
                UUID productId = entry.getKey();
                Integer requestedQuantity = entry.getValue();

                // Fetch product details
                ProductDTO product = productMapper.toDto(productService.findById(productId.toString()));

                if (product.getTotalQuantity() == null || product.getTotalQuantity() < requestedQuantity) {
                    // Product verification failed
                    // Log the failure
                    logger.log(Level.INFO,
                            "Product verification failed for orderId={0}, productId={1}, requestedQuantity={2}",
                            new Object[] { checkOrderRequestBody.getOrderId(), productId, requestedQuantity });

                    // Handle the case where product verification fails (e.g., throw an exception or
                    // log an error)
                    // You can customize this part based on your application's requirements.
                    // For example, you might want to throw a specific exception indicating the
                    // failure.
                    throw new Exception("Product verification failed for productId=" + productId);
                }

                // Log product verification success
                logger.log(Level.INFO,
                        "Product verification successful for orderId={0}, productId={1}, requestedQuantity={2}",
                        new Object[] { checkOrderRequestBody.getOrderId(), productId, requestedQuantity });
                product.setTotalQuantity(product.getTotalQuantity() - requestedQuantity);
                // Update the stock quantity
                productService.update(productMapper.toEntity(product));
                // Log the stock liberation
                logger.log(Level.INFO, "Stock liberated for orderId={0}, productId={1}, liberatedQuantity={2}",
                        new Object[] { checkOrderRequestBody.getOrderId(), productId, requestedQuantity });
            }

            // Log the completion of the stock liberation process
            logger.log(Level.INFO, "Stock liberation completed for orderId={0}", checkOrderRequestBody.getOrderId());

        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE,
                    "Error in liberateProducts method for orderId=" + checkOrderRequestBody.getOrderId(), e);

            // Handle the exception (e.g., throw a specific exception or log an error)
            // You can customize this part based on your application's requirements.
            // For example, you might want to throw a specific exception indicating the
            // failure.
            throw new Exception("Stock liberation failed for orderId=" + checkOrderRequestBody.getOrderId(), e);
        }
    }

}
