package com.maisproximo.app_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LojaDto {
    private Long id;
    private String nome;
    private Long cnpjOrCpf;
}