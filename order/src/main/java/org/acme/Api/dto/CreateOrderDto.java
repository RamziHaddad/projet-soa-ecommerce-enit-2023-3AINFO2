package org.acme.Api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.acme.domain.model.OrderItem;

import java.util.List;
@Getter
@AllArgsConstructor
public class CreateOrderDto {
    private String customer ;
    private List<OrderItem> items;
    private int cardNumber;
    private  int cardCode;

}
