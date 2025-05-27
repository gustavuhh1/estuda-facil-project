package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MensagemFactory{


    public Mensagem criarMensagem(MensagemDTO dto, Usuario remetente, Usuario destinatario) {
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setLida(false);
        return mensagem;
    }
}
