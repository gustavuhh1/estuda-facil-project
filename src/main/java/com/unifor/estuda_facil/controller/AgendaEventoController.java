package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.entity.AgendaEvento;
import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
import com.unifor.estuda_facil.service.AgendaEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agenda")
class AgendaEventoController {
    private final AgendaEventoService service;
    public AgendaEventoController(AgendaEventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> criarEvento(@RequestBody AgendaEventoDTO dto) {
        service.criarEvento(dto);
        return ResponseEntity.ok("Evento criado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<AgendaEvento>> listarPorTurma(@RequestParam UUID turmaId) {
        return ResponseEntity.ok(service.listarPorTurma(turmaId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<AgendaEvento>> listarPorProfessor(@PathVariable UUID professorId) {
        return ResponseEntity.ok(service.listarPorProfessor(professorId));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<AgendaEvento>> listarAgendaDoAluno(@PathVariable UUID alunoId, @RequestParam UUID turmaId) {
        return ResponseEntity.ok(service.listarAgendaDoAluno(alunoId, turmaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarEvento(@PathVariable UUID id, @RequestBody AgendaEventoDTO dto) {
        service.editarEvento(id, dto);
        return ResponseEntity.ok("Evento atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEvento(@PathVariable UUID id) {
        service.deletarEvento(id);
        return ResponseEntity.ok("Evento deletado com sucesso.");
    }
}