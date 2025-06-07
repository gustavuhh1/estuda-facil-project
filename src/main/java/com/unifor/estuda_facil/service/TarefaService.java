package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.factory.TarefaFactory;
import com.unifor.estuda_facil.models.dto.TarefaDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import com.unifor.estuda_facil.repository.TarefaRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;

    @Loggable
    public List<TarefaDTO> listarTodas() {
        return tarefaRepository.findAll().stream()
                .map(t -> new TarefaDTO(t.getId(), t.getTitulo(), t.getDescricao(), t.getDataEntrega(), t.getDisciplina(), Optional.ofNullable(t.getTurma()).map(Turma::getId).orElse(null), t.getProfessor().getId()))
                .collect(Collectors.toList());
    }

    @Loggable
    public TarefaDTO buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .map(t -> new TarefaDTO(t.getId(), t.getTitulo(), t.getDescricao(), t.getDataEntrega(), t.getDisciplina(), Optional.ofNullable(t.getTurma()).map(Turma::getId).orElse(null), t.getProfessor().getId()))
                .orElse(null);
    }

    @Loggable
    public TarefaDTO criar(TarefaDTO tarefaDTO) {
        Turma turma = turmaRepository.findById(tarefaDTO.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Professor professor = professorRepository.findById(tarefaDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

//        Tarefa tarefa = new Tarefa(tarefaDTO.getId(), tarefaDTO.getTitulo(), tarefaDTO.getDescricao(), tarefaDTO.getDataEntrega(), tarefaDTO.getDisciplina(), turma, professor);
        Tarefa tarefa = TarefaFactory.criarTarefa(tarefaDTO, turma, professor);
        tarefa = tarefaRepository.save(tarefa);

        return new TarefaDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataEntrega(), tarefa.getDisciplina(), tarefa.getTurma().getId(), tarefa.getProfessor().getId());
    }

    @Loggable
    public TarefaDTO atualizar(Long id, TarefaDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        TarefaFactory.atualizarTarefa(tarefa, dto, turma, professor);

        tarefa = tarefaRepository.save(tarefa);

        return new TarefaDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataEntrega(), tarefa.getDisciplina(), tarefa.getTurma().getId(), tarefa.getProfessor().getId());
    }

    @Loggable
    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }
}
