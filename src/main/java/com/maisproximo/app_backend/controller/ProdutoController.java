package com.maisproximo.app_backend.controller;

import com.maisproximo.app_backend.dto.ProdutoDto;
import com.maisproximo.app_backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    //Construir uploadImageToProduto REST API
    @PostMapping("{id}/image")
    public ResponseEntity<ProdutoDto> uploadImageToProduto(@PathVariable("id") Long produtoId,
                                              @RequestBody MultipartFile image) throws IOException {
        ProdutoDto produtoComImagem = produtoService.uploadImageToProduto(produtoId, image);
        return ResponseEntity.ok(produtoComImagem);
    }

    @GetMapping("{id}/image")
    public ResponseEntity<?> downloadImageFromProduto(@PathVariable("id") Long produtoId) throws IOException {
        byte[] image = produtoService.downloadImageFromProduto(produtoId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

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
