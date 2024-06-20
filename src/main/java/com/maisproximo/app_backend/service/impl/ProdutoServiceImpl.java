package com.maisproximo.app_backend.service.impl;

import com.maisproximo.app_backend.dto.ProdutoDto;
import com.maisproximo.app_backend.entity.Loja;
import com.maisproximo.app_backend.entity.Produto;
import com.maisproximo.app_backend.exception.ResourceNotFoundException;
import com.maisproximo.app_backend.mapper.LojaMapper;
import com.maisproximo.app_backend.mapper.ProdutoMapper;
import com.maisproximo.app_backend.repository.ProdutoRepository;
import com.maisproximo.app_backend.service.LojaService;
import com.maisproximo.app_backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {
    private ProdutoRepository produtoRepository;
    private LojaService lojaService;

    @Override
    public ProdutoDto createProduto(ProdutoDto produtoDto) {
        Loja loja = LojaMapper.mapToLoja(lojaService.getLojaById(produtoDto.getLojaId()));
        Produto produto = ProdutoMapper.mapToProduto(produtoDto, loja);
        Produto savedProduto = produtoRepository.save(produto);
        return ProdutoMapper.mapToProdutoDto(savedProduto);

    }

    @Override
    public ProdutoDto getProdutoById(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto com o id informado não existe: " + produtoId));
        return ProdutoMapper.mapToProdutoDto(produto);
    }

    @Override
    public List<ProdutoDto> getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoMapper::mapToProdutoDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDto updateProduto(Long produtoId, ProdutoDto updateProduto) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ResourceNotFoundException("Produto com o id informado não existe: " + produtoId)
        );

        produto.setNome(updateProduto.getNome());
        produto.setPreco(updateProduto.getPreco());

        Produto updatedProdutoObj = produtoRepository.save(produto);

        return ProdutoMapper.mapToProdutoDto(updatedProdutoObj);
    }

    @Override
    public void deleteProduto(Long produtoId) {

        produtoRepository.findById(produtoId).orElseThrow(
                () -> new ResourceNotFoundException("Produto com o id informado não existe: " + produtoId)
        );

        produtoRepository.deleteById(produtoId);

    }
}
