package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.entity.Anexo;
import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.repository.AnexoRepository;
import com.unifor.estuda_facil.repository.AvisoRepository;
import com.unifor.estuda_facil.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;
    private final TarefaRepository tarefaRepository;
    private final AvisoRepository avisoRepository;

    private final String uploadDir = "uploads/";

    public Anexo salvarAnexoParaTarefa(Long tarefaId, MultipartFile file) throws IOException {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(tarefaId);
        if (tarefaOpt.isEmpty()) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }

        String caminho = salvarArquivoNoDisco(file);

        Anexo anexo = new Anexo();
        anexo.setNomeArquivo(file.getOriginalFilename());
        anexo.setCaminhoArquivo(caminho);
        anexo.setTipo(file.getContentType());
        anexo.setTamanho(file.getSize());
        anexo.setTarefa(tarefaOpt.get());

        return anexoRepository.save(anexo);
    }

    public Anexo salvarAnexoParaAviso(Long avisoId, MultipartFile file) throws IOException {
        Optional<Aviso> avisoOpt = avisoRepository.findById(avisoId);
        if (avisoOpt.isEmpty()) {
            throw new IllegalArgumentException("Aviso não encontrado");
        }

        String caminho = salvarArquivoNoDisco(file);

        Anexo anexo = new Anexo();
        anexo.setNomeArquivo(file.getOriginalFilename());
        anexo.setCaminhoArquivo(caminho);
        anexo.setTipo(file.getContentType());
        anexo.setTamanho(file.getSize());
        anexo.setAviso(avisoOpt.get());

        return anexoRepository.save(anexo);
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

    public List<Anexo> listarAnexosPorTarefa(Long tarefaId) {
        return anexoRepository.findByTarefaId(tarefaId);
    }

    public List<Anexo> listarAnexosPorAviso(Long avisoId) {
        return anexoRepository.findByAvisoId(avisoId);
    }

    public byte[] downloadArquivo(Long anexoId) throws IOException {
        Anexo anexo = anexoRepository.findById(anexoId)
                .orElseThrow(() -> new IllegalArgumentException("Anexo não encontrado"));

        Path caminhoArquivo = Paths.get(anexo.getCaminhoArquivo());
        return Files.readAllBytes(caminhoArquivo);
    }
}
