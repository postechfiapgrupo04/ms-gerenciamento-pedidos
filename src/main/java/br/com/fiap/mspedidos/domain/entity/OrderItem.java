package br.com.fiap.mspedidos.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity(name = "itens_pedido")
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "cod_item", nullable = false)
    private String codItem;

    @Column(name = "quantidade", nullable = false)
    private Long quantity;

    @Column(name = "preco_unitario", nullable = false)
    private Double price;

    @ManyToOne
    private Order order;

}
