package com.maisproximo.app_backend.dto;

import com.maisproximo.app_backend.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LojaDto {
    private Long id;
    private String nome;
    private String cnpjOrCpf;
    private String imagePath;
    private Set<Produto> produtos;
}
