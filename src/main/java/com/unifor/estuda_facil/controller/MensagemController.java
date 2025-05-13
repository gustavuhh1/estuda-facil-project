package com.unifor.estuda_facil.controller;

import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Responsavel;
import com.unifor.estuda_facil.repository.MensagemRepository;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import com.unifor.estuda_facil.repository.ResponsavelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;


    @PostMapping
    public ResponseEntity<String> enviarMensagem(@RequestBody MensagemDTO dto) {
        Optional<Professor> professorOpt = professorRepository.findById(dto.getProfessorId());
        Optional<Responsavel> responsavelOpt = responsavelRepository.findById(dto.getResponsavelId());

        if (professorOpt.isEmpty() || responsavelOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Professor ou responsável não encontrado.");
        }

        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(professorOpt.get());
        mensagem.setDestinatario(responsavelOpt.get());
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setLida(false);

        mensagemRepository.save(mensagem);
        return ResponseEntity.ok("Mensagem enviada com sucesso.");
    }

    // GET /mensagens/recebidas?responsavelId=123
    @GetMapping("/recebidas")
    public ResponseEntity<List<Mensagem>> listarMensagensRecebidas(@RequestParam Long responsavelId) {
        Optional<Responsavel> responsavelOpt = responsavelRepository.findById(responsavelId);
        if (responsavelOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Mensagem> mensagens = mensagemRepository
                .findByDestinatarioOrderByDataEnvioDesc(responsavelOpt.get());

        return ResponseEntity.ok(mensagens);
    }
    // PUT /mensagens/{id}/ler
    @PutMapping("/{id}/ler")
    public ResponseEntity<String> marcarComoLida(@PathVariable Long id) {
        Optional<Mensagem> mensagemOpt = mensagemRepository.findById(id);

        if (mensagemOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Mensagem mensagem = mensagemOpt.get();
        if (!mensagem.isLida()) {
            mensagem.setLida(true);
            mensagemRepository.save(mensagem);
        }

        return ResponseEntity.ok("Mensagem marcada como lida.");
    }

}
