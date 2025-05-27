package com.unifor.estuda_facil.models.dto;

import lombok.Data;


@Data
public class AnexoDTO {
    private String nomeArquivo;
    private String caminhoArquivo;
    private String tipo;
    private Long tamanho;
    private Long tarefaId;
    private Long avisoId;
}

