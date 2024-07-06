package br.com.fiap.mspedidos.application.service;

import br.com.fiap.mspedidos.application.rest.converter.OrderMapper;
import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;
import br.com.fiap.mspedidos.domain.exception.AppException;
import br.com.fiap.mspedidos.domain.usecase.OrderUseCase;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderConsumerService {

    private final OrderUseCase orderUseCase;
    private final OrderMapper orderMapper;

    public OrderConsumerService(OrderUseCase orderUseCase, OrderMapper orderMapper) {
        this.orderUseCase = orderUseCase;
        this.orderMapper = orderMapper;
    }

    @KafkaListener(topics = "order-logistic", groupId = "teste")
    public void flightEventConsumer(@NotNull OrderDTO order) throws AppException {
        log.info("Checking message from logistics -> {}", order.getIdOrder());
        orderUseCase.updateOrder(order.getIdOrder(), orderMapper.OrderDTOToOrder(order));
        log.info("Updating order -> {}", order.getIdOrder());
    }

}
