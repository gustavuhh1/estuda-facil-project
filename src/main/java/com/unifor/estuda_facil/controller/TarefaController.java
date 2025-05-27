package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.TarefaDTO;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.service.TarefaService;
import com.unifor.estuda_facil.service.TurmaService;
import com.unifor.estuda_facil.factory.TarefaFactory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tarefa")
class TarefaController {
    private final TarefaService tarefaService;
    private final TurmaService turmaService;
    public TarefaController(TarefaService tarefaService, TurmaService turmaService) {
        this.tarefaService = tarefaService;
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody @Valid TarefaDTO dto) {
        Turma turma = dto.getTurmaId() != null ? turmaService.buscarPorId(dto.getTurmaId()).orElse(null) : null;
        Tarefa tarefa = TarefaFactory.criarTarefa(dto, turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvar(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable UUID id) {
        return tarefaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        return ResponseEntity.ok(tarefaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable UUID id, @RequestBody @Valid TarefaDTO dto) {
        Optional<Tarefa> opt = tarefaService.buscarPorId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Turma turma = dto.getTurmaId() != null ? turmaService.buscarPorId(dto.getTurmaId()).orElse(null) : null;
        Tarefa tarefa = TarefaFactory.atualizarTarefa(opt.get(), dto, turma);
        return ResponseEntity.ok(tarefaService.salvar(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

