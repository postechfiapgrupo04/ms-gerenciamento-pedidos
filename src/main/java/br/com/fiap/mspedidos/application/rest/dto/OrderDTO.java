package br.com.fiap.mspedidos.application.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {

    private String idOrder;
    private String customerId;
    private String status;
    private List<OrderItemDTO> items;

}
