package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.AlunoConclusaoDTO;
import com.unifor.estuda_facil.models.dto.AlunoTarefaDTO;
import com.unifor.estuda_facil.models.dto.TarefaConcluidaDTO;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.models.entity.AlunoTarefa;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.repository.AlunoRepository;
import com.unifor.estuda_facil.repository.AlunoTarefaRepository;
import com.unifor.estuda_facil.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlunoTarefaService {

    private final AlunoTarefaRepository alunoTarefaRepository;
    private final AlunoRepository alunoRepository;
    private final TarefaRepository tarefaRepository;

    public AlunoTarefaService(AlunoTarefaRepository alunoTarefaRepository,
                              AlunoRepository alunoRepository,
                              TarefaRepository tarefaRepository) {
        this.alunoTarefaRepository = alunoTarefaRepository;
        this.alunoRepository = alunoRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public AlunoTarefa marcarComoConcluida(UUID alunoId, Long tarefaId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        AlunoTarefa alunoTarefa = alunoTarefaRepository
                .findByAlunoIdAndTarefaId(alunoId, tarefaId)
                .orElse(new AlunoTarefa());

        alunoTarefa.setAluno(aluno);
        alunoTarefa.setTarefa(tarefa);
        alunoTarefa.setConcluida(true);
        alunoTarefa.setDataConclusao(LocalDateTime.now());

        return alunoTarefaRepository.save(alunoTarefa);
    }

    public AlunoTarefa desmarcarComoConcluida(UUID alunoId, Long tarefaId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        AlunoTarefa alunoTarefa = alunoTarefaRepository.findByAlunoIdAndTarefaId(alunoId, tarefaId)
                .orElseThrow(() -> new RuntimeException("Relacionamento Aluno-Tarefa não encontrado"));

        alunoTarefa.setConcluida(false);
        alunoTarefa.setDataConclusao(null);

        return alunoTarefaRepository.save(alunoTarefa);
    }

    public List<TarefaConcluidaDTO> listarTarefasConcluidasDTO(UUID alunoId) {
        return alunoTarefaRepository.findByAlunoId(alunoId).stream()
                .filter(AlunoTarefa::isConcluida)
                .map(at -> new TarefaConcluidaDTO(
                        at.getTarefa().getId(),
                        at.getTarefa().getTitulo(),
                        at.getDataConclusao()
                ))
                .collect(Collectors.toList());
    }

    public List<AlunoConclusaoDTO> listarAlunosQueConcluiramTarefa(Long tarefaId) {
        return alunoTarefaRepository.findByTarefaIdAndConcluidaTrue(tarefaId).stream()
                .map(at -> new AlunoConclusaoDTO(
                        at.getAluno().getId(),
                        at.getAluno().getNome(),
                        at.getDataConclusao()
                ))
                .collect(Collectors.toList());
    }

    public List<TarefaConcluidaDTO> listarTarefasConcluidasPorAluno(UUID alunoId) {
        return alunoTarefaRepository.findByAlunoIdAndConcluidaTrue(alunoId).stream()
                .map(at -> new TarefaConcluidaDTO(
                        at.getTarefa().getId(),
                        at.getTarefa().getTitulo(),
                        at.getDataConclusao()
                ))
                .collect(Collectors.toList());
    }
}

