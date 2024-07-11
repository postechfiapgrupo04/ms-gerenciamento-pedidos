package br.com.fiap.mspedidos.domain.usecase;

import br.com.fiap.mspedidos.application.rest.converter.OrderMapper;
import br.com.fiap.mspedidos.application.service.OrderProducerService;
import br.com.fiap.mspedidos.application.service.integration.StockService;
import br.com.fiap.mspedidos.domain.entity.Order;
import br.com.fiap.mspedidos.domain.entity.OrderItem;
import br.com.fiap.mspedidos.domain.enums.OrderStatus;
import br.com.fiap.mspedidos.domain.exception.AppException;
import br.com.fiap.mspedidos.domain.exception.constants.ErrorConstants;
import br.com.fiap.mspedidos.infrastructure.data.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(OrderUseCase.class);
    private final OrderRepository orderRepository;
    private final OrderProducerService orderProducerService;
    private final OrderMapper orderMapper;
    private final StockService stockService;

    public OrderUseCase(OrderRepository orderRepository, OrderProducerService orderProducerService, OrderMapper orderMapper, StockService stockService) {
        this.orderRepository = orderRepository;
        this.orderProducerService = orderProducerService;
        this.orderMapper = orderMapper;
        this.stockService = stockService;
    }

    public Order createOrder(Order order) throws AppException {
        for (OrderItem orderItem : order.getItems()) {
            stockService.getStock(orderItem.getCodItem());
        }
        for (OrderItem item : order.getItems()) {
            stockService.updateStock(item.getCodItem(), item.getQuantity().intValue());
        }


        Order newOrder = orderRepository.save(order);
        /*
         * Create a message to send to the logistic service
         * */
        orderProducerService.sendOrderEventToLogistic(orderMapper.OrderToOrderDTO(order));

        return newOrder;
    }

    public Order getOrder(String orderId) throws AppException {

        Optional<Order> order = orderRepository.findById(UUID.fromString(orderId));

        if (order.isPresent()) {
            return order.get();
        }
        throw new AppException(ErrorConstants.ORDER_NOT_FOUND);
    }

    public Order updateOrder(String orderId, Order orderDTO) throws AppException {
        Order newOrder = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() -> new AppException(ErrorConstants.ORDER_NOT_FOUND));
        /*
         * Create a message to send to the logistic service
         * */
        orderProducerService.sendOrderEventToLogistic(orderMapper.OrderToOrderDTO(newOrder));
        newOrder.setStatus(orderDTO.getStatus());
        newOrder.setCustomerId(orderDTO.getCustomerId());
        newOrder.setItems(orderDTO.getItems());
        return orderRepository.save(newOrder);
    }

    public void deleteOrder(String orderId) throws AppException {
        Order newOrder = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() -> new AppException(ErrorConstants.ORDER_NOT_FOUND));
        /*
         * Create a message to send to the logistic service
         * */
        newOrder.setStatus(OrderStatus.CANCELED);
        orderProducerService.sendOrderEventToLogistic(orderMapper.OrderToOrderDTO(newOrder));
    }

    public Order changeOrderStatus(String orderId, String status) throws AppException {
        Order newOrder = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() -> new AppException(ErrorConstants.ORDER_NOT_FOUND));
        /*
         * Create a message to send to the logistic service
         * */
        newOrder.setStatus(OrderStatus.valueOf(status));
        orderProducerService.sendOrderEventToLogistic(orderMapper.OrderToOrderDTO(newOrder));
        return orderRepository.save(newOrder);
    }

    public List<Order> getOrderByStatus(String status) {
        return orderRepository.findAllByStatus(OrderStatus.valueOf(status));
    }

    public List<Order> getOrderByCustomerId(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }
}
