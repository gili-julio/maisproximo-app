package com.maisproximo.app_backend.mapper;

import com.maisproximo.app_backend.dto.ProdutoDto;
import com.maisproximo.app_backend.entity.Loja;
import com.maisproximo.app_backend.entity.Produto;

public class ProdutoMapper {
    public static ProdutoDto mapToProdutoDto(Produto produto){
        return new ProdutoDto(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getLoja().getId()
        );
    }

    public static Produto mapToProduto(ProdutoDto produtoDto, Loja loja){
        return new Produto(
                produtoDto.getId(),
                produtoDto.getNome(),
                produtoDto.getPreco(),
                loja
        );
    }
}
