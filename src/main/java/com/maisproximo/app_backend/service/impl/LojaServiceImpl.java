package com.maisproximo.app_backend.service.impl;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.entity.Loja;
import com.maisproximo.app_backend.exception.ResourceNotFoundException;
import com.maisproximo.app_backend.mapper.LojaMapper;
import com.maisproximo.app_backend.repository.LojaRepository;
import com.maisproximo.app_backend.service.LojaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LojaServiceImpl implements LojaService {

    private LojaRepository lojaRepository;

    @Override
    public LojaDto createLoja(LojaDto lojaDto) {

        Loja loja = LojaMapper.mapToLoja(lojaDto);
        Loja savedLoja = lojaRepository.save(loja);
        return LojaMapper.mapToLojaDto(savedLoja);

    }

    @Override
    public LojaDto getLojaById(Long lojaId) {

        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loja com o id informado não existe: " + lojaId));
        return LojaMapper.mapToLojaDto(loja);

    }

    @Override
    public List<LojaDto> getAllLojas() {
        List<Loja> lojas = lojaRepository.findAll();
        return lojas.stream().map((loja) -> LojaMapper.mapToLojaDto(loja))
                .collect(Collectors.toList());
    }
}
