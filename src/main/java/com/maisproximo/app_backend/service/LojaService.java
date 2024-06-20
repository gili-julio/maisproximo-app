package com.maisproximo.app_backend.service;

import com.maisproximo.app_backend.dto.LojaDto;

import java.util.List;

public interface LojaService {
    LojaDto createLoja(LojaDto lojaDto);

    LojaDto getLojaById(Long id);

    List<LojaDto> getAllLojas();

    LojaDto updateLoja(Long lojaId, LojaDto updateLoja);

    void deleteLoja(Long lojaId);
}
