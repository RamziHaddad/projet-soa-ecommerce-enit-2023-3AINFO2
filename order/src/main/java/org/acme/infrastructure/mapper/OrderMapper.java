package org.acme.infrastructure.mapper;

import org.acme.domain.model.Order;
import org.acme.domain.model.dto.CreateOrderDto;
import org.acme.domain.model.dto.OrderViewDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "quarkus")
public interface OrderMapper {
    OrderViewDto mapToDto(Order entity);

    Order mapToDomain(CreateOrderDto dto);

}
