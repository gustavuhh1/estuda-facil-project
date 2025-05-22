package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody @Valid AlunoDTO dto) {
        Aluno a = service.criarAluno(dto);
        return ResponseEntity.status(201).body(a);
    }

    @PutMapping("/{alunoId}/turma/{turmaId}")
    public ResponseEntity<String> atribuirTurma(@PathVariable Long alunoId, @PathVariable Long turmaId) {
        service.atribuirTurma(alunoId, turmaId);
        return ResponseEntity.ok("Turma atribuída ao aluno com sucesso!");
    }



    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        return ResponseEntity.ok(service.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        Aluno a = service.buscarPorId(id);
        if (a == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlunoDTO dto
    ) {
        Aluno updated = service.atualizarAluno(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
