package br.com.fiap.mspedidos.application.service.integration;

import br.com.fiap.mspedidos.application.rest.dto.ProdutoAtualizaEstoqueRequest;
import br.com.fiap.mspedidos.application.rest.dto.ProdutoResponse;
import br.com.fiap.mspedidos.domain.exception.AppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    @Value("${app.integration.product_url}")
    private String stockUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public ProdutoResponse getStock(String productId) throws AppException {

        ResponseEntity<ProdutoResponse> response = restTemplate.getForEntity(stockUrl + "/" + productId, ProdutoResponse.class);
        if (response.getStatusCode().isError())
            throw new AppException("Erro ao buscar produto no estoque");
        return response.getBody();
    }

    public void updateStock(String productId, Integer quantity) throws AppException {
        ProdutoAtualizaEstoqueRequest produtoAtualizaEstoqueRequest = new ProdutoAtualizaEstoqueRequest(quantity);
        try {
            restTemplate.postForEntity(stockUrl + "/" + productId + "/saida-estoque", produtoAtualizaEstoqueRequest, ProdutoResponse.class);
        } catch (Exception e) {
            throw new AppException("Não foi possível atualizar o estoque do produto");
        }
    }

}
