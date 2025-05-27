package com.unifor.estuda_facil.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MensagemDTO {
    private UUID remetenteId;
    private UUID destinatarioId;
    private Long respostaParaId;
    private String conteudo;


}
