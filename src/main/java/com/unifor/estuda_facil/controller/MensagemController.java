package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.service.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mensagens")
class MensagemController {
    private final MensagemService service;
    public MensagemController(MensagemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> enviarMensagem(@RequestBody MensagemDTO dto) {
        service.enviarMensagem(dto);
        return ResponseEntity.ok("Mensagem enviada com sucesso.");
    }

    @GetMapping("/recebidas")
    public ResponseEntity<List<Mensagem>> listarRecebidas(@RequestParam UUID responsavelId) {
        return ResponseEntity.ok(service.listarRecebidas(responsavelId));
    }

    @PutMapping("/{id}/ler")
    public ResponseEntity<String> marcarComoLida(@PathVariable UUID id) {
        service.marcarComoLida(id);
        return ResponseEntity.ok("Mensagem marcada como lida.");
    }

    @GetMapping("/nao-lidas")
    public ResponseEntity<Long> contarNaoLidas(@RequestParam UUID responsavelId) {
        return ResponseEntity.ok(service.contarNaoLidas(responsavelId));
    }

    @GetMapping("/conversa")
    public ResponseEntity<List<Mensagem>> obterConversa(@RequestParam UUID professorId, @RequestParam UUID responsavelId) {
        return ResponseEntity.ok(service.obterConversa(professorId, responsavelId));
    }
}
