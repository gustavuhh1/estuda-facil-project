package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
class ProfessorController {
    private final ProfessorService service;
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody @Valid ProfessorDTO dto) {
        return ResponseEntity.status(201).body(service.criarProfessor(dto));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listar() {
        return ResponseEntity.ok(service.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable UUID id, @RequestBody @Valid ProfessorDTO dto) {
        return ResponseEntity.ok(service.atualizarProfessor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}