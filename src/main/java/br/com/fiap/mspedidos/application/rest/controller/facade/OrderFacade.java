package br.com.fiap.mspedidos.application.rest.controller.facade;

import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;
import br.com.fiap.mspedidos.domain.exception.AppException;

import java.util.List;

public interface OrderFacade {

    OrderDTO createOrder(OrderDTO orderDTO) throws AppException;

    OrderDTO getOrder(String orderId) throws AppException;

    OrderDTO updateOrder(String orderId, OrderDTO orderDTO) throws AppException;

    void deleteOrder(String orderId) throws AppException;

    OrderDTO changeOrderStatus(String orderId, String status) throws AppException;

    List<OrderDTO> getOrderByStatus(String status);

    List<OrderDTO> getOrderByCustomerId(String customerId);

}
