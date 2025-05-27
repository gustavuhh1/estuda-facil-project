package com.unifor.estuda_facil.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AnexoDTO {
    private String nomeArquivo;
    private String caminhoArquivo;
    private String tipo;
    private Long tamanho;
    private UUID tarefaId;
    private UUID avisoId;
}

