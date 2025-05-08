package com.unifor.estuda_facil.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @GetMapping("/tarefa")
    public ResponseEntity<List<?>> visualizarTarefas(
            @RequestParam(required = false) Map<String, String> filtros) {
        // TODO: aplicar filtros e retornar lista de TarefaDTO
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping(value = "/anexo", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> adicionarAnexo(
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") JsonNode meta) {
        // TODO: salvar anexo via service
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mensagem")
    public ResponseEntity<List<?>> verMensagens() {
        // TODO: lista de MensagemDTO
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping("/aviso")
    public ResponseEntity<Void> mandarAvisoTurma(@RequestBody JsonNode payload) {
        // TODO: enviar aviso via service
        return ResponseEntity.ok().build();
    }
}