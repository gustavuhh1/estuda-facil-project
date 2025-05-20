package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.AgendaEvento;
import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
import com.unifor.estuda_facil.service.AgendaEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaEventoController {

    private final AgendaEventoService agendaEventoService;

    public AgendaEventoController(AgendaEventoService agendaEventoService) {
        this.agendaEventoService = agendaEventoService;
    }

    @PostMapping
    public ResponseEntity<String> criarEvento(@RequestBody AgendaEventoDTO dto) {
        agendaEventoService.criarEvento(dto);
        return ResponseEntity.ok("Evento criado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<AgendaEvento>> listarPorTurma(@RequestParam Long turmaId) {
        return ResponseEntity.ok(agendaEventoService.listarPorTurma(turmaId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<AgendaEvento>> listarPorProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(agendaEventoService.listarPorProfessor(professorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarEvento(@PathVariable Long id, @RequestBody AgendaEventoDTO dto) {
        agendaEventoService.editarEvento(id, dto);
        return ResponseEntity.ok("Evento atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEvento(@PathVariable Long id) {
        agendaEventoService.deletarEvento(id);
        return ResponseEntity.ok("Evento deletado com sucesso.");
    }
}
