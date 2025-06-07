package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.dto.TurmaDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('COORDENACAO')")
    public ResponseEntity<Professor> criar(@RequestBody @Valid ProfessorDTO dto) {
        Professor p = service.criarProfessor(dto);
        return ResponseEntity.status(201).body(p);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<Professor>> listar() {
        return ResponseEntity.ok(service.listarProfessores());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<Professor> buscar(@PathVariable UUID id) {
        Professor p = service.buscarPorId(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO')")
    public ResponseEntity<Professor> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid ProfessorDTO dto
    ) {
        Professor updated = service.atualizarProfessor(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/turmas")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<List<TurmaDTO>> turmasDoProfessor(@PathVariable UUID id) {
        return ResponseEntity.ok(service.turmasDoProfessor(id));
    }
}