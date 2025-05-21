package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Responsavel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MensagemFactoryImpl implements MensagemFactory {

    @Override
    public Mensagem criarMensagem(MensagemDTO dto, Professor professor, Responsavel responsavel) {
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(professor);
        mensagem.setDestinatario(responsavel);
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setLida(false);
        return mensagem;
    }
}
