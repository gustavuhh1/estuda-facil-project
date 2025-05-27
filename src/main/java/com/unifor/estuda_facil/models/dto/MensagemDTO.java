package com.unifor.estuda_facil.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MensagemDTO {
    private UUID professorId;
    private UUID responsavelId;
    private String conteudo;
    private UUID respostaParaId;
}
