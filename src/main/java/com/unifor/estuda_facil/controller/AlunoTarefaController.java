package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.AlunoTarefaDTO;
import com.unifor.estuda_facil.models.dto.TarefaConcluidaDTO;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.models.entity.AlunoTarefa;
import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.service.AlunoTarefaService;
import com.unifor.estuda_facil.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefa")
@PreAuthorize("hasRole('ALUNO')")
public class AlunoTarefaController {

    private final AlunoTarefaService alunoTarefaService;

    public AlunoTarefaController(AlunoTarefaService alunoTarefaService) {
        this.alunoTarefaService = alunoTarefaService;
    }

    @PostMapping("/{tarefaId}/concluir")
    public ResponseEntity<AlunoTarefaDTO> concluirTarefa(@PathVariable Long tarefaId,
                                                         @AuthenticationPrincipal Usuario usuario) {
        UUID alunoId = usuario.getId();
        AlunoTarefa tarefaConcluida = alunoTarefaService.marcarComoConcluida(alunoId, tarefaId);

        AlunoTarefaDTO dto = new AlunoTarefaDTO(
                tarefaConcluida.getTarefa().getId(),
                tarefaConcluida.getTarefa().getTitulo(),
                true,
                tarefaConcluida.getDataConclusao()
        );

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{tarefaId}/concluir")
    public ResponseEntity<AlunoTarefaDTO> desmarcarTarefaConcluida(@PathVariable Long tarefaId,
                                                                   @AuthenticationPrincipal Usuario usuario) {
        AlunoTarefa at = alunoTarefaService.desmarcarComoConcluida(usuario.getId(), tarefaId);

        AlunoTarefaDTO dto = new AlunoTarefaDTO(
                at.getTarefa().getId(),
                at.getTarefa().getTitulo(),
                at.isConcluida(),
                at.getDataConclusao()
        );

        return ResponseEntity.ok(dto);
    }


    @GetMapping("/concluidas")
    public ResponseEntity<List<TarefaConcluidaDTO>> listarTarefasConcluidas(@AuthenticationPrincipal Usuario usuario) {
        UUID alunoId = usuario.getId();
        List<TarefaConcluidaDTO> tarefas = alunoTarefaService.listarTarefasConcluidasDTO(alunoId);
        return ResponseEntity.ok(tarefas);
    }
}
