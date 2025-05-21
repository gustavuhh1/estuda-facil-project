package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.MensagemDTO;
import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Responsavel;

public interface MensagemFactory {
    Mensagem criarMensagem(MensagemDTO dto, Professor professor, Responsavel responsavel);
}
