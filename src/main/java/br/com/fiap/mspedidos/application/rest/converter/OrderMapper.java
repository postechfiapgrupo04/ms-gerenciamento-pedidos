package br.com.fiap.mspedidos.application.rest.converter;

import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;
import br.com.fiap.mspedidos.application.rest.dto.OrderItemDTO;
import br.com.fiap.mspedidos.domain.entity.Order;
import br.com.fiap.mspedidos.domain.entity.OrderItem;
import br.com.fiap.mspedidos.domain.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderDTO OrderToOrderDTO(Order order) {

        return OrderDTO.builder()
                .idOrder(order.getIdOrder().toString())
                .customerId(order.getCustomerId())
                .status(order.getStatus().toString())
                .items(order.getItems().stream().map(this::toOrderItemDTO).collect(Collectors.toList()))
                .build();
    }

    public Order OrderDTOToOrder(OrderDTO orderDTO) {

        return Order.builder()
                .customerId(orderDTO.getCustomerId())
                .status(OrderStatus.valueOf(orderDTO.getStatus()))
                .items(orderDTO.getItems().stream().map(this::toOrderItem).collect(Collectors.toList()))
                .build();
    }


    private OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .productId(orderItem.getCodItem())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }

    private OrderItem toOrderItem(OrderItemDTO orderItemDTO) {
        return OrderItem.builder()
                .codItem(orderItemDTO.getProductId())
                .quantity(orderItemDTO.getQuantity())
                .price(orderItemDTO.getPrice())
                .build();
    }
}
