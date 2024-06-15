package br.com.fiap.mspedidos.application.rest.controller.facade;

import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;

import java.util.List;

public interface OrderFacade {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrder(String orderId);

    OrderDTO updateOrder(String orderId, OrderDTO orderDTO);

    void deleteOrder(String orderId);

    OrderDTO changeOrderStatus(String orderId, String status);

    List<OrderDTO> getOrderByStatus(String status);

    List<OrderDTO> getOrderByCustomerId(String customerId);

}
