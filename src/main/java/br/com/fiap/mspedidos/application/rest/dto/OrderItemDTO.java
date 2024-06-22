package br.com.fiap.mspedidos.application.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDTO {

    private String productId;
    private Long quantity;
    private Double price;

}
