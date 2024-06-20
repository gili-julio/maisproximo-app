package com.maisproximo.app_backend.controller;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.service.LojaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
