package com.maisproximo.app_backend.controller;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.dto.ProdutoDto;
import com.maisproximo.app_backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    //Construir Add Produto REST API
    @PostMapping
    public ResponseEntity<ProdutoDto> createProduto(@RequestBody ProdutoDto produtoDto) {
        ProdutoDto savedProduto = produtoService.createProduto(produtoDto);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    //Construir Get Produto REST API
    @GetMapping("{id}")
    public ResponseEntity<ProdutoDto> getProdutoById(@PathVariable("id") Long produtoId) {
        ProdutoDto produtoDto = produtoService.getProdutoById(produtoId);
        return ResponseEntity.ok(produtoDto);
    }

    //Construir Get All Produtos REST API
    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getAllProduto() {
        List<ProdutoDto> produtos = produtoService.getAllProdutos();
        return ResponseEntity.ok(produtos);
    }

    //Construir Update Produto REST API
    @PutMapping("{id}")
    public ResponseEntity<ProdutoDto> updateProduto(@PathVariable("id") Long produtoId,
                                              @RequestBody ProdutoDto updatedProduto) {

        ProdutoDto produtoDto = produtoService.updateProduto(produtoId, updatedProduto);
        return ResponseEntity.ok(produtoDto);

    }

    //Construir Delete Loja REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable("id") Long produtoId) {
        produtoService.deleteProduto(produtoId);
        return ResponseEntity.ok("Produto deletado com sucesso!");
    }

}
