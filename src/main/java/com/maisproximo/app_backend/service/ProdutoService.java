package com.maisproximo.app_backend.service;

import com.maisproximo.app_backend.dto.ProdutoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProdutoService {
    ProdutoDto createProduto(ProdutoDto produtoDto);

    ProdutoDto getProdutoById(Long produtoId);

    List<ProdutoDto> getAllProdutos();

    ProdutoDto updateProduto(Long produtoId, ProdutoDto updateProduto);

    void deleteProduto(Long produtoId);

    ProdutoDto uploadImageToProduto(Long produtoId, MultipartFile image) throws IOException;

    byte[] downloadImageFromProduto(Long produtoId) throws IOException;
}
