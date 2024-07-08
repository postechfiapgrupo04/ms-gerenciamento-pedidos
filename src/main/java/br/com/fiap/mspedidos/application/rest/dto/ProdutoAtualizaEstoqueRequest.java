package br.com.fiap.mspedidos.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoAtualizaEstoqueRequest {
    private Integer quantidade;
}
