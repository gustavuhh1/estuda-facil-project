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

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;

    @Loggable
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    @Loggable
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }


    @Loggable
    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }


    @Loggable
    public Tarefa atualizar(Long id, TarefaDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        TarefaFactory.atualizarTarefa(tarefa, dto, turma, professor);

        return tarefaRepository.save(tarefa);
    }


    @Loggable
    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }
}
