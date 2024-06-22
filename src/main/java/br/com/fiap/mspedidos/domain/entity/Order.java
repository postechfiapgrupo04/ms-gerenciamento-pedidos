package br.com.fiap.mspedidos.domain.entity;

import br.com.fiap.mspedidos.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num_pedido", nullable = false)
    private UUID idOrder;

    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "cliente_id", nullable = false)
    private String customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items;

}

