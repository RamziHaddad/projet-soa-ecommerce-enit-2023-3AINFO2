package org.acme.infrastructure.mapper;

import org.acme.Api.dto.CreateOrderDto;
import org.acme.Api.dto.OrderViewDto;
import org.acme.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "quarkus")
public interface OrderMapper {
    OrderViewDto mapToDto(Order entity);

    Order mapToDomain(CreateOrderDto dto);

}
