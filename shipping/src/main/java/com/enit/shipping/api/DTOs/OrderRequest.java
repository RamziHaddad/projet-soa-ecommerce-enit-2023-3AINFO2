package com.enit.shipping.api.DTOs;

import lombok.Data;

@Data
public class OrderRequest {
    private Long idOrder;
    private Long idUser;
    private String address;
}
