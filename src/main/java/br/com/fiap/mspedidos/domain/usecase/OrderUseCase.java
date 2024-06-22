package br.com.fiap.mspedidos.domain.usecase;

import br.com.fiap.mspedidos.domain.entity.Order;
import br.com.fiap.mspedidos.domain.enums.OrderStatus;
import br.com.fiap.mspedidos.domain.exception.AppException;
import br.com.fiap.mspedidos.domain.exception.constants.ErrorConstants;
import br.com.fiap.mspedidos.infrastructure.data.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderUseCase {

    private final OrderRepository orderRepository;

    public OrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        //TODO criate a service that calls the storage service to remove the item quantity from the stock
        //TODO create a service that calls the message service to send a message to the customer and the transportation service
        return orderRepository.save(order);
    }

    public Order getOrder(String orderId) throws AppException {

        Optional<Order> order = orderRepository.findById(UUID.fromString(orderId));

        if (order.isPresent()) {
            return order.get();
        }
        throw new AppException(ErrorConstants.ORDER_NOT_FOUND);
    }

    public Order updateOrder(String orderId, Order orderDTO) throws AppException {
        //TODO criate a service that calls the storage service to remove the item quantity from the stock
        //TODO create a service that calls the message service to send a message to the customer and the transportation service
        Optional<Order> order = orderRepository.findById(UUID.fromString(orderId));

        if (order.isPresent()) {
            return orderRepository.save(orderDTO);
        }
        throw new AppException(ErrorConstants.ORDER_NOT_FOUND);
    }

    public void deleteOrder(String orderId) throws AppException {
        //TODO criate a service that calls the storage service to remove the item quantity from the stock
        //TODO create a service that calls the message service to send a message to the customer and the transportation service
        orderRepository.findById(UUID.fromString(orderId))
                .ifPresent(orderRepository::delete);
        throw new AppException(ErrorConstants.ORDER_NOT_FOUND);
    }

    public Order changeOrderStatus(String orderId, String status) throws AppException {
        //TODO criate a service that calls the storage service to remove the item quantity from the stock
        //TODO create a service that calls the message service to send a message to the customer and the transportation service
        Optional<Order> order = orderRepository.findById(UUID.fromString(orderId));

        if (order.isPresent()) {
            order.get().setStatus(OrderStatus.valueOf(status));
            return orderRepository.save(order.get());
        }
        throw new AppException(ErrorConstants.ORDER_NOT_FOUND);
    }

    public List<Order> getOrderByStatus(String status) {
        return orderRepository.findAllByStatus(OrderStatus.valueOf(status));
    }

    public List<Order> getOrderByCustomerId(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }
}
