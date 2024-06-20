package com.maisproximo.app_backend.service;

import com.maisproximo.app_backend.dto.LojaDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LojaService {
    LojaDto createLoja(LojaDto lojaDto);

    LojaDto getLojaById(Long id);

    List<LojaDto> getAllLojas();

    LojaDto updateLoja(Long lojaId, LojaDto updateLoja);

    void deleteLoja(Long lojaId);

    LojaDto uploadImageToLoja(Long lojaId, MultipartFile image) throws IOException;

    byte[] downloadImageFromLoja(Long lojaId) throws IOException;
}
