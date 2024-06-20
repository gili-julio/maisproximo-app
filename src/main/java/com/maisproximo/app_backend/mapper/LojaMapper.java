package com.maisproximo.app_backend.mapper;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.entity.Loja;

public class LojaMapper {
    public static LojaDto mapToLojaDto(Loja loja){
        return new LojaDto(
                loja.getId(),
                loja.getNome(),
                loja.getCnpjOrCpf(),
                loja.getProdutos()
        );
    }

    public static Loja mapToLoja(LojaDto lojaDto){
        return new Loja(
                lojaDto.getId(),
                lojaDto.getNome(),
                lojaDto.getCnpjOrCpf(),
                lojaDto.getProdutos()
        );
    }
}
