package com.unifor.estuda_facil.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AlunoConclusaoDTO {
    private UUID alunoId;
    private String nome;
    private LocalDateTime dataConclusao;

    public AlunoConclusaoDTO(UUID alunoId, String nome, LocalDateTime dataConclusao) {
        this.alunoId = alunoId;
        this.nome = nome;
        this.dataConclusao = dataConclusao;
    }
}
