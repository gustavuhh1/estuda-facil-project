package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.aspect.Validate;
import com.unifor.estuda_facil.factory.AgendaEventoFactory;
import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
import com.unifor.estuda_facil.models.entity.AgendaEvento;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.repository.AgendaEventoRepository;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaEventoService {

    private final AgendaEventoRepository agendaEventoRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final AgendaEventoFactory agendaEventoFactory;

    @Loggable
    @Validate
    public AgendaEvento criarEvento(AgendaEventoDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        AgendaEvento evento = agendaEventoFactory.criar(dto, professor, turma);
        return agendaEventoRepository.save(evento);
    }

    @Loggable
    public List<AgendaEvento> listarPorTurma(Long turmaId) {
        if (!turmaRepository.existsById(turmaId)) {
            throw new RuntimeException("Turma não encontrada");
        }
        return agendaEventoRepository.findByTurmaIdOrderByDataEventoAsc(turmaId);
    }

    @Loggable
    public List<AgendaEvento> listarPorProfessor(Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new RuntimeException("Professor não encontrado");
        }
        return agendaEventoRepository.findByProfessorIdOrderByDataEventoAsc(professorId);
    }

    @Loggable
    @Validate
    public AgendaEvento editarEvento(Long id, AgendaEventoDTO dto) {
        AgendaEvento evento = agendaEventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        agendaEventoFactory.atualizar(evento, dto, professor, turma);
        return agendaEventoRepository.save(evento);
    }

    @Loggable
    public void deletarEvento(Long id) {
        if (!agendaEventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        agendaEventoRepository.deleteById(id);
    }
}
