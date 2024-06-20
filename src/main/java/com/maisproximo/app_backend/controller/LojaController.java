package com.maisproximo.app_backend.controller;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.entity.Loja;
import com.maisproximo.app_backend.service.LojaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/lojas")
public class LojaController {

    private LojaService lojaService;

    private final String IMAGE_FOLDER_PATH=
            "C:/Users/Superframe Force/Downloads/app-backend/src/main/resources/static/";

    //Construir uploadImageToLoja REST API
    @PostMapping("{id}/image")
    public ResponseEntity<LojaDto> uploadImageToLoja(@PathVariable("id") Long lojaId,
                                              @RequestBody MultipartFile image) throws IOException {
        LojaDto lojaComImagem = lojaService.uploadImageToLoja(lojaId, image);
        return ResponseEntity.ok(lojaComImagem);
    }

    @GetMapping("{id}/image")
    public ResponseEntity<?> downloadImageFromLoja(@PathVariable("id") Long lojaId) throws IOException {
        byte[] image = lojaService.downloadImageFromLoja(lojaId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);

    }

    //Construir Add Loja REST API
    @PostMapping
    public ResponseEntity<LojaDto> createLoja(@RequestBody LojaDto lojaDto) {
        LojaDto savedLoja = lojaService.createLoja(lojaDto);
        return new ResponseEntity<>(savedLoja, HttpStatus.CREATED);
    }

    //Construir Get Loja REST API
    @GetMapping("{id}")
    public ResponseEntity<LojaDto> getLojaById(@PathVariable("id") Long lojaId) {
        LojaDto lojaDto = lojaService.getLojaById(lojaId);
        return ResponseEntity.ok(lojaDto);
    }

    //Construir Get All Lojas REST API
    @GetMapping
    public ResponseEntity<List<LojaDto>> getAllLojas() {
        List<LojaDto> lojas = lojaService.getAllLojas();
        return ResponseEntity.ok(lojas);
    }

    //Construir Update Loja REST API
    @PutMapping("{id}")
    public ResponseEntity<LojaDto> updateLoja(@PathVariable("id") Long lojaId,
                                              @RequestBody LojaDto updatedLoja) {
        LojaDto lojaDto = lojaService.updateLoja(lojaId, updatedLoja);
        return ResponseEntity.ok(lojaDto);
    }

    //Construir Delete Loja REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLoja(@PathVariable("id") Long lojaId) {
        lojaService.deleteLoja(lojaId);
        return ResponseEntity.ok("Loja deletada com sucesso!");
    }

}
