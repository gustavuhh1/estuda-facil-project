package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody @Valid ProfessorDTO dto) {
        Professor p = service.criarProfessor(dto);
        return ResponseEntity.status(201).body(p);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listar() {
        return ResponseEntity.ok(service.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscar(@PathVariable Long id) {
        Professor p = service.buscarPorId(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorDTO dto
    ) {
        Professor updated = service.atualizarProfessor(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
