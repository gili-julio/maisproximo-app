package com.maisproximo.app_backend.service.impl;

import com.maisproximo.app_backend.dto.LojaDto;
import com.maisproximo.app_backend.entity.Loja;
import com.maisproximo.app_backend.entity.Produto;
import com.maisproximo.app_backend.exception.ResourceNotFoundException;
import com.maisproximo.app_backend.mapper.LojaMapper;
import com.maisproximo.app_backend.repository.LojaRepository;
import com.maisproximo.app_backend.service.LojaService;
import com.maisproximo.app_backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LojaServiceImpl implements LojaService {

    private LojaRepository lojaRepository;

    private final String IMAGE_FOLDER_PATH=
            "C:/Users/Superframe Force/Downloads/app-backend/src/main/resources/static/";

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
        return lojas.stream().map(LojaMapper::mapToLojaDto)
                .collect(Collectors.toList());

    }

    @Override
    public LojaDto updateLoja(Long lojaId, LojaDto updateLoja) {

        Loja loja = lojaRepository.findById(lojaId).orElseThrow(
                () -> new ResourceNotFoundException("Loja com o id informado não existe: " + lojaId)
        );

        loja.setNome(updateLoja.getNome());
        loja.setCnpjOrCpf(updateLoja.getCnpjOrCpf());
        loja.setImagePath(updateLoja.getImagePath());

        Loja updatedLojaObj = lojaRepository.save(loja);

        return LojaMapper.mapToLojaDto(updatedLojaObj);

    }

    @Override
    public void deleteLoja(Long lojaId) throws IOException {

        Loja loja = lojaRepository.findById(lojaId).orElseThrow(
                () -> new ResourceNotFoundException("Loja com o id informado não existe: " + lojaId)
        );

        if(loja.getImagePath()!=null) {
            File file = new File(IMAGE_FOLDER_PATH+loja.getImagePath());
            try {
                Files.delete(file.toPath());
            } catch (NoSuchFileException x) {
                System.err.format("%s: não foi encontrado arquivo ou diretorio%n", file.toPath());
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s não vazio%n", file.toPath());
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }
        Set<Produto> produtos = loja.getProdutos();

        for (Produto produto : produtos) {
            if(produto.getImagePath()!=null) {
                File file = new File(IMAGE_FOLDER_PATH+"produtos/"+produto.getImagePath());
                Files.delete(file.toPath());
            }
        }

        lojaRepository.deleteById(lojaId);

    }

    @Override
    public LojaDto uploadImageToLoja(Long lojaId, MultipartFile image) throws IOException {
        String imagePath = IMAGE_FOLDER_PATH
                +"loja("+lojaId+")."
                +image.getContentType().replaceAll("(?i)image/", "");
        Loja loja = lojaRepository.findById(lojaId).orElseThrow(
                () -> new ResourceNotFoundException("Loja com o id informado não existe: " + lojaId)
        );
        loja.setImagePath(
                "loja("+lojaId+")."+image.getContentType().replaceAll("(?i)image/", "")
        );
        image.transferTo(new File(imagePath));
        Loja updatedLojaObj = lojaRepository.save(loja);
        return LojaMapper.mapToLojaDto(updatedLojaObj);
    }

    @Override
    public byte[] downloadImageFromLoja(Long lojaId) throws IOException {
        Loja loja = lojaRepository.findById(lojaId).orElseThrow(
                () -> new ResourceNotFoundException("Loja com o id informado não existe: " + lojaId)
        );
        LojaDto lojaDto = LojaMapper.mapToLojaDto(loja);
        String imagePath = IMAGE_FOLDER_PATH+lojaDto.getImagePath();
        if(lojaDto.getImagePath()==null) {
            return null;
        }
        return Files.readAllBytes(new File(imagePath).toPath());
    }


}
