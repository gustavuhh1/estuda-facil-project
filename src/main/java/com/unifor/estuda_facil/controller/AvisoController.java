package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.AvisoDTO;
import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.models.entity.Turma;
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
public class AvisoController {

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

        if (dto.getDataCriacao() != null) {
            a.setDataCriacao(dto.getDataCriacao());
        } else {
            a.setDataCriacao(LocalDateTime.now());
        }

        if (dto.getTurmaId() != null) {
            Optional<Turma> turOpt = turmaService.buscarPorId(dto.getTurmaId());
            turOpt.ifPresent(a::setTurma);
        }

        Aviso saved = service.salvar(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Aviso> buscar(@PathVariable Long id) {
        Optional<Aviso> aOpt = service.buscarPorId(id);
        if (aOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aOpt.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<?> listarPorTurma(@RequestParam(required = false) Long turmaId) {
        if (turmaId != null) {
            return ResponseEntity.ok(service.listarPorTurmaOuGerais(turmaId));
        } else {
            return ResponseEntity.ok(service.listarTodosOrdenado());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AvisoDTO dto) {
        Optional<Aviso> avisoOpt = service.buscarPorId(id);
        if (avisoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Aviso aviso = avisoOpt.get();
        aviso.setTitulo(dto.getTitulo());
        aviso.setDescricao(dto.getDescricao());

        // Atualiza a data se for passada (senão mantém)
        if (dto.getDataCriacao() != null) {
            aviso.setDataCriacao(dto.getDataCriacao());
        }

        // Atualiza a turma se passada
        if (dto.getTurmaId() != null) {
            Optional<Turma> turmaOpt = turmaService.buscarPorId(dto.getTurmaId());
            turmaOpt.ifPresent(aviso::setTurma);
        } else {
            aviso.setTurma(null); // aviso geral
        }

        Aviso atualizado = service.salvar(aviso);
        return ResponseEntity.ok(atualizado);
    }


}
