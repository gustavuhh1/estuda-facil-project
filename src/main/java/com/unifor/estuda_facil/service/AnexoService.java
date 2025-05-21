package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.factory.AnexoFactory;
import com.unifor.estuda_facil.models.dto.AnexoDTO;
import com.unifor.estuda_facil.models.entity.Anexo;
import com.unifor.estuda_facil.repository.AnexoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;
    private final AnexoFactory anexoFactory;

    private final String uploadDir = "uploads/";

    @Loggable
    public Anexo salvarAnexoParaTarefa(Long tarefaId, MultipartFile file) throws IOException {
        String caminho = salvarArquivoNoDisco(file);

        AnexoDTO dto = new AnexoDTO();
        dto.setTarefaId(tarefaId);
        dto.setNomeArquivo(file.getOriginalFilename());
        dto.setCaminhoArquivo(caminho);
        dto.setTipo(file.getContentType());
        dto.setTamanho(file.getSize());

        return anexoRepository.save(anexoFactory.criar(dto));
    }

    @Loggable
    public Anexo salvarAnexoParaAviso(Long avisoId, MultipartFile file) throws IOException {
        String caminho = salvarArquivoNoDisco(file);

        AnexoDTO dto = new AnexoDTO();
        dto.setAvisoId(avisoId);
        dto.setNomeArquivo(file.getOriginalFilename());
        dto.setCaminhoArquivo(caminho);
        dto.setTipo(file.getContentType());
        dto.setTamanho(file.getSize());

        return anexoRepository.save(anexoFactory.criar(dto));
    }

    private String salvarArquivoNoDisco(MultipartFile file) throws IOException {
        Path pastaUpload = Paths.get(uploadDir);
        if (!Files.exists(pastaUpload)) {
            Files.createDirectories(pastaUpload);
        }

        Path caminhoArquivo = pastaUpload.resolve(file.getOriginalFilename());
        file.transferTo(caminhoArquivo.toFile());
        return caminhoArquivo.toString();
    }

    @Loggable
    public List<Anexo> listarAnexosPorTarefa(Long tarefaId) {
        return anexoRepository.findByTarefaId(tarefaId);
    }

    @Loggable
    public List<Anexo> listarAnexosPorAviso(Long avisoId) {
        return anexoRepository.findByAvisoId(avisoId);
    }

    @Loggable
    public byte[] downloadArquivo(Long anexoId) throws IOException {
        Anexo anexo = anexoRepository.findById(anexoId)
                .orElseThrow(() -> new IllegalArgumentException("Anexo não encontrado"));

        Path caminhoArquivo = Paths.get(anexo.getCaminhoArquivo());
        return Files.readAllBytes(caminhoArquivo);
    }
}
