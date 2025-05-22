package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.AnexoDTO;
import com.unifor.estuda_facil.models.entity.Anexo;
import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.repository.AvisoRepository;
import com.unifor.estuda_facil.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnexoFactory{

    private final TarefaRepository tarefaRepository;
    private final AvisoRepository avisoRepository;

    public Anexo criar(AnexoDTO dto) {
        Anexo anexo = new Anexo();
        anexo.setNomeArquivo(dto.getNomeArquivo());
        anexo.setCaminhoArquivo(dto.getCaminhoArquivo());
        anexo.setTipo(dto.getTipo());
        anexo.setTamanho(dto.getTamanho());

        if (dto.getTarefaId() != null) {
            Tarefa tarefa = tarefaRepository.findById(dto.getTarefaId())
                    .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
            anexo.setTarefa(tarefa);
        }

        if (dto.getAvisoId() != null) {
            Aviso aviso = avisoRepository.findById(dto.getAvisoId())
                    .orElseThrow(() -> new IllegalArgumentException("Aviso não encontrada"));
            anexo.setAviso(aviso);
        }

        return anexo;
    }
}
