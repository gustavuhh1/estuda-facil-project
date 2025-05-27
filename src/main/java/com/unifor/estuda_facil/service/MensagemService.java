package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.factory.MensagemFactory;
import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Usuario;
import com.unifor.estuda_facil.repository.MensagemRepository;
import com.unifor.estuda_facil.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final MensagemFactory mensagemFactory;


    @Loggable
    public void enviarMensagem(MensagemDTO dto) {
        Usuario remetente = usuarioRepository.findById(dto.getRemetenteId())
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));

        Usuario destinatario = usuarioRepository.findById(dto.getDestinatarioId())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        Mensagem mensagem = mensagemFactory.criarMensagem(dto, remetente, destinatario);

        if (dto.getRespostaParaId() != null) {
            mensagemRepository.findById(dto.getRespostaParaId())
                    .ifPresent(mensagem::setRespostaPara);
        }

        mensagemRepository.save(mensagem);
    }

    @Loggable
    public List<Mensagem> listarRecebidas(UUID destinatarioId) {
        Usuario destinatario = usuarioRepository.findById(destinatarioId)
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        return mensagemRepository.findByDestinatarioOrderByDataEnvioDesc(destinatario);
    }
    @Loggable
    public void marcarComoLida(Long id) {
        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));

        if (!mensagem.isLida()) {
            mensagem.setLida(true);
            mensagemRepository.save(mensagem);
        }
    }
    @Loggable
    public Long contarNaoLidas(UUID destinatarioId) {
        if (!usuarioRepository.existsById(destinatarioId)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return mensagemRepository.countByDestinatarioIdAndLidaFalse(destinatarioId);
    }
    @Loggable
    public List<Mensagem> obterConversa(UUID usuario1Id, UUID usuario2Id) {
        if (!usuarioRepository.existsById(usuario1Id) || !usuarioRepository.existsById(usuario2Id)) {
            throw new RuntimeException("Usuários não encontrados");
        }

        return mensagemRepository.buscarConversaEntre(usuario1Id, usuario2Id);
    }
}
