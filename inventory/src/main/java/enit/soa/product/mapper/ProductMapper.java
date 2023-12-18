package enit.soa.product.mapper;

import enit.soa.product.dto.ProductDTO;
import enit.soa.product.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTotalQuantity(),
                product.getRequestedQuantity()
        );
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTotalQuantity(productDTO.getTotalQuantity());
        product.setRequestedQuantity(productDTO.getRequestedQuantity());
        return product;
    }
}

