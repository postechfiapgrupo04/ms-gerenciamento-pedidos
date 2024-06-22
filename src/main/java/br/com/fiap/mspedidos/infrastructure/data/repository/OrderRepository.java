package br.com.fiap.mspedidos.infrastructure.data.repository;

import br.com.fiap.mspedidos.domain.entity.Order;
import br.com.fiap.mspedidos.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    
    List<Order> findAllByStatus(OrderStatus orderStatus);

    List<Order> findAllByCustomerId(String uuid);
}
