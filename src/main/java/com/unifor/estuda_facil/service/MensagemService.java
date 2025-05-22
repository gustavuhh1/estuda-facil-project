package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Responsavel;
import com.unifor.estuda_facil.repository.MensagemRepository;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import com.unifor.estuda_facil.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final ProfessorRepository professorRepository;
    private final ResponsavelRepository responsavelRepository;

    public void enviarMensagem(MensagemDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Responsavel responsavel = responsavelRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(professor);
        mensagem.setDestinatario(responsavel);
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setLida(false);

        if (dto.getRespostaParaId() != null) {
            mensagemRepository.findById(dto.getRespostaParaId())
                    .ifPresent(mensagem::setRespostaPara);
        }

        mensagemRepository.save(mensagem);
    }

    public List<Mensagem> listarRecebidas(Long responsavelId) {
        Responsavel responsavel = responsavelRepository.findById(responsavelId)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        return mensagemRepository.findByDestinatarioOrderByDataEnvioDesc(responsavel);
    }

    public void marcarComoLida(Long id) {
        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));

        if (!mensagem.isLida()) {
            mensagem.setLida(true);
            mensagemRepository.save(mensagem);
        }
    }

    public long contarNaoLidas(Long responsavelId) {
        if (!responsavelRepository.existsById(responsavelId)) {
            throw new RuntimeException("Responsável não encontrado");
        }
        return mensagemRepository.countByDestinatarioIdAndLidaFalse(responsavelId);
    }

    public List<Mensagem> obterConversa(Long professorId, Long responsavelId) {
        if (!professorRepository.existsById(professorId)) {
            throw new RuntimeException("Professor não encontrado");
        }
        if (!responsavelRepository.existsById(responsavelId)) {
            throw new RuntimeException("Responsável não encontrado");
        }

        return mensagemRepository.buscarConversaEntre(professorId, responsavelId);
    }
}
