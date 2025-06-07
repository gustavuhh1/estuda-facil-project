package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.TurmaDTO;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/turma")
class TurmaController {
    private final TurmaService service;
    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDENACAO')")
    public ResponseEntity<Turma> criar(@RequestBody @Valid TurmaDTO dto) {
        Turma t = new Turma();
        t.setCodigo(dto.getCodigo());
        t.setNome(dto.getNome());
        t.setAnoLetivo(dto.getAnoLetivo());
        return ResponseEntity.status(201).body(service.salvar(t));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<Turma>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<Turma> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody @Valid TurmaDTO dto) {
        Turma turma = service.buscarPorId(id).orElse(null);
        if (turma == null) return ResponseEntity.notFound().build();
        turma.setCodigo(dto.getCodigo());
        turma.setNome(dto.getNome());
        turma.setAnoLetivo(dto.getAnoLetivo());
        return ResponseEntity.ok(service.salvar(turma));
    }

    @PutMapping("/{turmaId}/professor/{professorId}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<Turma> adicionarProfessor(
            @PathVariable Long turmaId,
            @PathVariable UUID professorId) {

        Turma turmaAtualizada = service.adicionarProfessor(turmaId, professorId);
        return ResponseEntity.ok(turmaAtualizada);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
