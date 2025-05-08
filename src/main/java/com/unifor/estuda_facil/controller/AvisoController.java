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
        a.setDataCriacao(dto.getDataCriacao());
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
}
