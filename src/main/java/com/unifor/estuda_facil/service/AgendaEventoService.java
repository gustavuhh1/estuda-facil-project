package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.AgendaEvento;
import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
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

    public AgendaEvento criarEvento(AgendaEventoDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        AgendaEvento evento = new AgendaEvento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setTipo(dto.getTipo());
        evento.setProfessor(professor);
        evento.setTurma(turma);

        return agendaEventoRepository.save(evento);
    }

    public List<AgendaEvento> listarPorTurma(Long turmaId) {
        if (!turmaRepository.existsById(turmaId)) {
            throw new RuntimeException("Turma não encontrada");
        }
        return agendaEventoRepository.findByTurmaIdOrderByDataEventoAsc(turmaId);
    }

    public List<AgendaEvento> listarPorProfessor(Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new RuntimeException("Professor não encontrado");
        }
        return agendaEventoRepository.findByProfessorIdOrderByDataEventoAsc(professorId);
    }

    public AgendaEvento editarEvento(Long id, AgendaEventoDTO dto) {
        AgendaEvento evento = agendaEventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setTipo(dto.getTipo());
        evento.setProfessor(professor);
        evento.setTurma(turma);

        return agendaEventoRepository.save(evento);
    }

    public void deletarEvento(Long id) {
        if (!agendaEventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        agendaEventoRepository.deleteById(id);
    }
}
