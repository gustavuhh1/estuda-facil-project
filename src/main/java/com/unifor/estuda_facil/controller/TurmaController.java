package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.TurmaDTO;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody @Valid TurmaDTO dto) {
        Turma t = new Turma();
        t.setCodigo(dto.getCodigo());
        t.setNome(dto.getNome());
        t.setAnoLetivo(dto.getAnoLetivo());
        Turma saved = service.salvar(t);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscar(@PathVariable Long id) {
        Turma t = service.buscarPorId(id).orElse(null);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TurmaDTO dto
    ) {
        Turma t = service.buscarPorId(id).orElse(null);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        t.setCodigo(dto.getCodigo());
        t.setNome(dto.getNome());
        t.setAnoLetivo(dto.getAnoLetivo());
        return ResponseEntity.ok(service.salvar(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
