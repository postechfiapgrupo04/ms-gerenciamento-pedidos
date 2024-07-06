package br.com.fiap.mspedidos.application.service;

import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class OrderProducerService {

    public static final String TOPIC = "order-logistic";

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendOrderEventToLogistic(OrderDTO order) {
        String key = order.getIdOrder();
        kafkaTemplate.send(TOPIC, key, order);
        log.info("Sent order update to logistic orderID {}", order.getIdOrder());
    }

}
