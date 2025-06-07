package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.entity.AgendaEvento;
import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
import com.unifor.estuda_facil.service.AgendaEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agenda")
public class AgendaEventoController {

    private final AgendaEventoService agendaEventoService;

    public AgendaEventoController(AgendaEventoService agendaEventoService) {
        this.agendaEventoService = agendaEventoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<String> criarEvento(@RequestBody AgendaEventoDTO dto) {
        agendaEventoService.criarEvento(dto);
        return ResponseEntity.ok("Evento criado com sucesso.");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<AgendaEvento>> listarPorTurma(@RequestParam Long turmaId) {
        return ResponseEntity.ok(agendaEventoService.listarPorTurma(turmaId));
    }

    @GetMapping("/professor/{professorId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<AgendaEvento>> listarPorProfessor(@PathVariable UUID professorId) {
        return ResponseEntity.ok(agendaEventoService.listarPorProfessor(professorId));
    }

    @GetMapping("/aluno/{alunoId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<AgendaEvento>> listarAgendaDoAluno(@PathVariable UUID alunoId, @RequestParam Long turmaId) {
        List<AgendaEvento> eventos = agendaEventoService.listarAgendaDoAluno(alunoId, turmaId);
        return ResponseEntity.ok(eventos);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<String> editarEvento(@PathVariable Long id, @RequestBody AgendaEventoDTO dto) {
        agendaEventoService.editarEvento(id, dto);
        return ResponseEntity.ok("Evento atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<String> deletarEvento(@PathVariable Long id) {
        agendaEventoService.deletarEvento(id);
        return ResponseEntity.ok("Evento deletado com sucesso.");
    }

}
