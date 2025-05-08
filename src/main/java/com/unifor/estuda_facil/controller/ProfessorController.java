package com.unifor.estuda_facil.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @PostMapping("/tarefa")
    public ResponseEntity<Void> criarTarefa(@RequestBody JsonNode payload) {
        // TODO: mapear payload para TarefaDTO e salvar via service
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tarefa")
    public ResponseEntity<List<?>> listarTarefas() {
        // TODO: buscar lista de TarefaDTO via service
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<Void> atualizarTarefa(
            @PathVariable Long id,
            @RequestBody JsonNode payload) {
        // TODO: atualizar tarefa via service
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tarefa/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        // TODO: deletar via service
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aviso")
    public ResponseEntity<Void> enviarAviso(@RequestBody JsonNode payload) {
        // TODO: payload contém “mensagem” e “destino” (id de turma ou usuário)
        return ResponseEntity.ok().build();
    }
}