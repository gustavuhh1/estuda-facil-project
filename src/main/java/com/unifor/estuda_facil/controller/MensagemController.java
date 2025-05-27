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
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping
    public ResponseEntity<String> enviarMensagem(@RequestBody MensagemDTO dto) {
        mensagemService.enviarMensagem(dto);
        return ResponseEntity.ok("Mensagem enviada com sucesso.");
    }

    @GetMapping("/recebidas")
    public ResponseEntity<List<Mensagem>> listarMensagensRecebidas(@RequestParam UUID destinatarioId) {
        return ResponseEntity.ok(mensagemService.listarRecebidas(destinatarioId));
    }

    @PutMapping("/{id}/ler")
    public ResponseEntity<String> marcarComoLida(@PathVariable Long id) {
        mensagemService.marcarComoLida(id);
        return ResponseEntity.ok("Mensagem marcada como lida.");
    }

    @GetMapping("/nao-lidas")
    public ResponseEntity<Long> contarNaoLidas(@RequestParam UUID responsavelId) {
        return ResponseEntity.ok(mensagemService.contarNaoLidas(responsavelId));
    }

    @GetMapping("/conversa")
    public ResponseEntity<List<Mensagem>> obterConversa(
            @RequestParam UUID usuario1Id,
            @RequestParam UUID usuario2Id) {
        return ResponseEntity.ok(mensagemService.obterConversa(usuario1Id, usuario2Id));
    }
}
