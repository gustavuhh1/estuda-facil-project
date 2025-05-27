package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.ResponsavelDTO;
import com.unifor.estuda_facil.models.entity.Responsavel;
import com.unifor.estuda_facil.service.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    private final ResponsavelService service;
    public ResponsavelController(ResponsavelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Responsavel> criar(@RequestBody @Valid ResponsavelDTO dto) {
        return ResponseEntity.status(201).body(service.criarResponsavel(dto));
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> atualizar(@PathVariable UUID id, @RequestBody @Valid ResponsavelDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
