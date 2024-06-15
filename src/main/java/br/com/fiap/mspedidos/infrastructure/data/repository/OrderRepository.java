package br.com.fiap.mspedidos.infrastructure.data.repository;

import br.com.fiap.mspedidos.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
