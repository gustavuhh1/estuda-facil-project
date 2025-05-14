package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.*;
import com.unifor.estuda_facil.models.AgendaEvento;
import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaEventoController {

    @Autowired
    private AgendaEventoRepository agendaEventoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    public ResponseEntity<String> criarEvento(@RequestBody AgendaEventoDTO dto) {
        Optional<Professor> professorOpt = professorRepository.findById(dto.getProfessorId());
        Optional<Turma> turmaOpt = turmaRepository.findById(dto.getTurmaId());

        if (professorOpt.isEmpty() || turmaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Professor ou turma não encontrados.");
        }

        AgendaEvento evento = new AgendaEvento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setTipo(dto.getTipo());
        evento.setProfessor(professorOpt.get());
        evento.setTurma(turmaOpt.get());

        agendaEventoRepository.save(evento);
        return ResponseEntity.ok("Evento criado com sucesso.");
    }
    @GetMapping
    public ResponseEntity<List<AgendaEvento>> listarPorTurma(@RequestParam Long turmaId) {
        if (!turmaRepository.existsById(turmaId)) {
            return ResponseEntity.badRequest().build();
        }

        List<AgendaEvento> eventos = agendaEventoRepository.findByTurmaIdOrderByDataEventoAsc(turmaId);
        return ResponseEntity.ok(eventos);
    }
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<AgendaEvento>> listarPorProfessor(@PathVariable Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            return ResponseEntity.badRequest().build();
        }

        List<AgendaEvento> eventos = agendaEventoRepository.findByProfessorIdOrderByDataEventoAsc(professorId);
        return ResponseEntity.ok(eventos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> editarEvento(@PathVariable Long id, @RequestBody AgendaEventoDTO dto) {
        Optional<AgendaEvento> eventoOpt = agendaEventoRepository.findById(id);
        Optional<Turma> turmaOpt = turmaRepository.findById(dto.getTurmaId());
        Optional<Professor> profOpt = professorRepository.findById(dto.getProfessorId());

        if (eventoOpt.isEmpty() || turmaOpt.isEmpty() || profOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Evento, turma ou professor não encontrados.");
        }

        AgendaEvento evento = eventoOpt.get();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setTipo(dto.getTipo());
        evento.setTurma(turmaOpt.get());
        evento.setProfessor(profOpt.get());

        agendaEventoRepository.save(evento);
        return ResponseEntity.ok("Evento atualizado com sucesso.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEvento(@PathVariable Long id) {
        if (!agendaEventoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        agendaEventoRepository.deleteById(id);
        return ResponseEntity.ok("Evento deletado com sucesso.");
    }


}
