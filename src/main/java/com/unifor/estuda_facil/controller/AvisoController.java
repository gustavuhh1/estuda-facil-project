package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.AvisoDTO;
import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.service.AvisoService;
import com.unifor.estuda_facil.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/aviso")
class AvisoController {
    private final AvisoService service;
    private final TurmaService turmaService;
    public AvisoController(AvisoService service, TurmaService turmaService) {
        this.service = service;
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Aviso> criar(@RequestBody @Valid AvisoDTO dto) {
        Aviso a = new Aviso();
        a.setTitulo(dto.getTitulo());
        a.setDescricao(dto.getDescricao());
        a.setDataCriacao(Optional.ofNullable(dto.getDataCriacao()).orElse(LocalDateTime.now()));
        if (dto.getTurmaId() != null) {
            turmaService.buscarPorId(dto.getTurmaId()).ifPresent(a::setTurma);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(a));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aviso> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> listarPorTurma(@RequestParam(required = false) Long turmaId) {
        return turmaId != null ? ResponseEntity.ok(service.listarPorTurmaOuGerais(turmaId)) : ResponseEntity.ok(service.listarTodosOrdenado());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AvisoDTO dto) {
        Optional<Aviso> avisoOpt = service.buscarPorId(id);
        if (avisoOpt.isEmpty()) return ResponseEntity.notFound().build();
        Aviso aviso = avisoOpt.get();
        aviso.setTitulo(dto.getTitulo());
        aviso.setDescricao(dto.getDescricao());
        aviso.setDataCriacao(Optional.ofNullable(dto.getDataCriacao()).orElse(aviso.getDataCriacao()));
        aviso.setTurma(null);
        if (dto.getTurmaId() != null) {
            turmaService.buscarPorId(dto.getTurmaId()).ifPresent(aviso::setTurma);
        }
        return ResponseEntity.ok(service.salvar(aviso));
    }
}

