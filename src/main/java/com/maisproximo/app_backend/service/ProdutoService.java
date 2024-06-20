package com.maisproximo.app_backend.service;

import com.maisproximo.app_backend.dto.ProdutoDto;

import java.util.List;

public interface ProdutoService {
    ProdutoDto createProduto(ProdutoDto produtoDto);

    ProdutoDto getProdutoById(Long produtoId);

    List<ProdutoDto> getAllProdutos();

    ProdutoDto updateProduto(Long produtoId, ProdutoDto updateProduto);

    void deleteProduto(Long produtoId);
}
