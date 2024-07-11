package br.com.fiap.mspedidos.application.rest.controller.facade.implementation;

import br.com.fiap.mspedidos.application.rest.controller.facade.OrderFacade;
import br.com.fiap.mspedidos.application.rest.converter.OrderMapper;
import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;
import br.com.fiap.mspedidos.domain.exception.AppException;
import br.com.fiap.mspedidos.domain.usecase.OrderUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFacadeImp implements OrderFacade {

    private final OrderMapper orderMapper;
    private final OrderUseCase orderUseCase;

    public OrderFacadeImp(OrderMapper orderMapper, OrderUseCase orderUseCase) {
        this.orderMapper = orderMapper;
        this.orderUseCase = orderUseCase;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws AppException {

        return orderMapper.
                OrderToOrderDTO(orderUseCase.
                        createOrder(orderMapper.
                                OrderDTOToOrder(orderDTO)));
    }

    @Override
    public OrderDTO getOrder(String orderId) throws AppException {
        return orderMapper.
                OrderToOrderDTO(orderUseCase.
                        getOrder(orderId));
    }

    @Override
    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) throws AppException {
        return orderMapper.
                OrderToOrderDTO(orderUseCase.
                        updateOrder(orderId, orderMapper.
                                OrderDTOToOrder(orderDTO)));
    }

    @Override
    public void deleteOrder(String orderId) throws AppException {
        orderUseCase.deleteOrder(orderId);
    }

    @Override
    public OrderDTO changeOrderStatus(String orderId, String status) throws AppException {
        return orderMapper.
                OrderToOrderDTO(orderUseCase.
                        changeOrderStatus(orderId, status));
    }

    @Override
    public List<OrderDTO> getOrderByStatus(String status) {
        return orderUseCase.
                getOrderByStatus(status).
                stream().
                map(orderMapper::OrderToOrderDTO).toList();
    }

    @Override
    public List<OrderDTO> getOrderByCustomerId(String customerId) {
        return orderUseCase.
                getOrderByCustomerId(customerId).
                stream().
                map(orderMapper::OrderToOrderDTO).toList();
    }
}
