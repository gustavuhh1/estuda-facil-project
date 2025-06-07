package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.dto.AlunoResponseDTO;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
        public ResponseEntity<Aluno> criar(@RequestBody AlunoResponseDTO dto) {
        Aluno a = service.criarAluno(dto);
        return ResponseEntity.status(201).body(a);
    }

    @PutMapping("/{alunoId}/turma/{turmaId}")
    public ResponseEntity<String> atribuirTurma(@PathVariable UUID alunoId, @PathVariable Long turmaId) {
        service.atribuirTurma(alunoId, turmaId);
        return ResponseEntity.ok("Turma atribuída ao aluno com sucesso!");
    }

    @GetMapping()  // Especificando o endpoint
    public ResponseEntity<List<AlunoResponseDTO>> listar() {
        try {
            List<AlunoResponseDTO> dtos = service.listarAlunos()
                    .stream()
                    .map(aluno -> new AlunoResponseDTO(
                            aluno.getId(),
                            aluno.getNome(),
                            aluno.getDataNascimento(),
                            aluno.getMatricula(),
                            aluno.getTurma().getId(),
                            aluno.getEmail(),
                            null // Não retornar a senha no DTO
                    ))
                    .collect(Collectors.toList());

            if (dtos.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 para lista vazia
            }

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500 para erros
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable UUID id) {
        Aluno a = service.buscarPorId(id);
        if (a == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AlunoDTO dto
    ) {
        Aluno updated = service.atualizarAluno(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
