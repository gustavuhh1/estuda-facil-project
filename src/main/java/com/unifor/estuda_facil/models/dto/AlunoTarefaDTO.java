package com.unifor.estuda_facil.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlunoTarefaDTO {

    private Long tarefaId;
    private String titulo;
    private boolean concluida;
    private LocalDateTime dataConclusao;

    public AlunoTarefaDTO(Long tarefaId, String titulo, boolean concluida, LocalDateTime dataConclusao) {
        this.tarefaId = tarefaId;
        this.titulo = titulo;
        this.concluida = concluida;
        this.dataConclusao = dataConclusao;
    }
}

