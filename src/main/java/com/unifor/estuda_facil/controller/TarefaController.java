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

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;
    private final TurmaService turmaService;

    public TarefaController(TarefaService tarefaService, TurmaService turmaService) {
        this.tarefaService = tarefaService;
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody @Valid TarefaDTO dto) {
        Turma turma = null;
        if (dto.getTurmaId() != null) {
            turma = turmaService.buscarPorId(dto.getTurmaId())
                    .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        }

        Tarefa tarefa = TarefaFactory.criarTarefa(dto, turma);
        Tarefa saved = tarefaService.salvar(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        return ResponseEntity.ok(tarefaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto) {
        Optional<Tarefa> optTarefa = tarefaService.buscarPorId(id);
        if (optTarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Turma turma = null;
        if (dto.getTurmaId() != null) {
            turma = turmaService.buscarPorId(dto.getTurmaId())
                    .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        }

        Tarefa tarefaAtualizada = TarefaFactory.atualizarTarefa(optTarefa.get(), dto, turma);
        Tarefa saved = tarefaService.salvar(tarefaAtualizada);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
