package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.TarefaDTO;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.service.TarefaService;
import com.unifor.estuda_facil.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService service;
    private final TurmaService turmaService;

    public TarefaController(TarefaService service, TurmaService turmaService) {
        this.service = service;
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody @Valid TarefaDTO dto) {
        Tarefa t = new Tarefa();
        t.setTitulo(dto.getTitulo());
        t.setDescricao(dto.getDescricao());
        t.setDataEntrega(dto.getDataEntrega());
        t.setStatus(dto.getStatus());
        t.setNota(dto.getNota());
        if (dto.getTurmaId() != null) {
            Optional<Turma> turOpt = turmaService.buscarPorId(dto.getTurmaId());
            turOpt.ifPresent(t::setTurma);
        }
        Tarefa saved = service.salvar(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        Optional<Tarefa> tOpt = service.buscarPorId(id);
        if (tOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tOpt.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TarefaDTO dto
    ) {
        Optional<Tarefa> tOpt = service.buscarPorId(id);
        if (tOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Tarefa existing = tOpt.get();
        existing.setTitulo(dto.getTitulo());
        existing.setDescricao(dto.getDescricao());
        existing.setDataEntrega(dto.getDataEntrega());
        existing.setStatus(dto.getStatus());
        existing.setNota(dto.getNota());
        if (dto.getTurmaId() != null) {
            Optional<Turma> turOpt = turmaService.buscarPorId(dto.getTurmaId());
            turOpt.ifPresent(existing::setTurma);
        }
        Tarefa updated = service.salvar(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
