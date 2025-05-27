package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.entity.*;
import com.unifor.estuda_facil.service.*;
import com.unifor.estuda_facil.factory.TarefaFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService service;
    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody @Valid AlunoDTO dto) {
        return ResponseEntity.status(201).body(service.criarAluno(dto));
    }

    @PutMapping("/{alunoId}/turma/{turmaId}")
    public ResponseEntity<String> atribuirTurma(@PathVariable UUID alunoId, @PathVariable UUID turmaId) {
        service.atribuirTurma(alunoId, turmaId);
        return ResponseEntity.ok("Turma atribuída ao aluno com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        return ResponseEntity.ok(service.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable UUID id, @RequestBody @Valid AlunoDTO dto) {
        return ResponseEntity.ok(service.atualizarAluno(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}