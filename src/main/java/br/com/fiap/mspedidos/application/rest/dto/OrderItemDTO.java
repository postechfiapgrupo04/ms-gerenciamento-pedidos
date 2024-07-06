package br.com.fiap.mspedidos.application.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private String productId;
    private Long quantity;
    private Double price;

}
