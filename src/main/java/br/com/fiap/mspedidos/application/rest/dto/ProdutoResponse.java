package br.com.fiap.mspedidos.application.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoResponse {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
}
