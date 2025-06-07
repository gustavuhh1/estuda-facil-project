package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.factory.TarefaFactory;
import com.unifor.estuda_facil.models.dto.TarefaDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.service.ProfessorService;
import com.unifor.estuda_facil.service.TarefaService;
import com.unifor.estuda_facil.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;
    private final TurmaService turmaService;
    private final ProfessorService professorService;

    @PostMapping
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<TarefaDTO> criar(@RequestBody @Valid TarefaDTO dto) {
//        Turma turma = null;
//        if (dto.getTurmaId() != null) {
//            turma = turmaService.buscarPorId(dto.getTurmaId())
//                    .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
//        }
//
//        Professor professor = null;
//        if (dto.getProfessorId() != null) {
//            professor = professorService.buscarPorId(dto.getProfessorId());
//        }
//
//        Tarefa tarefa = TarefaFactory.criarTarefa(dto, turma, professor);
        TarefaDTO saved = tarefaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<TarefaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<TarefaDTO>> listar() {
        return ResponseEntity.ok(tarefaService.listarTodas());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto) {
//        TarefaDTO optTarefa = tarefaService.buscarPorId(id);
//        if (optTarefa == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Turma turma = null;
//        if (dto.getTurmaId() != null) {
//            turma = turmaService.buscarPorId(dto.getTurmaId())
//                    .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
//        }
//
//        Professor professor = null;
//        if (dto.getProfessorId() != null) {
//            professor = professorService.buscarPorId(dto.getProfessorId());
//        }

//        Tarefa saved = TarefaFactory.atualizarTarefa(new Tarefa(), dto, turma, professor);
        return ResponseEntity.ok(tarefaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDENACAO', 'PROFESSOR')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
