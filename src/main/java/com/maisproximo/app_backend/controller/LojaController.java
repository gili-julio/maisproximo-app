package com.maisproximo.app_backend.controller;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.service.LojaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/lojas")
public class LojaController {

    private LojaService lojaService;

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

}
