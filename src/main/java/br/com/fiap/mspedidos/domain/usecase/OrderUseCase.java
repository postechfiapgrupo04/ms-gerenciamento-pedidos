package br.com.fiap.mspedidos.domain.usecase;

import br.com.fiap.mspedidos.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderUseCase {

    public Order createOrder(Order order) {

        return null;
    }

    public Order getOrder(String orderId) {
        return null;
    }

    public Order updateOrder(String orderId, Order orderDTO) {
        return null;
    }

    public void deleteOrder(String orderId) {

    }

    public Order changeOrderStatus(String orderId, String status) {
        return null;
    }

    public List<Order> getOrderByStatus(String status) {
        return List.of();
    }

    public List<Order> getOrderByCustomerId(String customerId) {
        return List.of();
    }
}
