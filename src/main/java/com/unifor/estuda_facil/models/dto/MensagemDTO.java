package com.unifor.estuda_facil.models.dto;

import lombok.Data;

@Data
public class MensagemDTO {
    private Long professorId;
    private Long responsavelId;
    private String conteudo;
}
