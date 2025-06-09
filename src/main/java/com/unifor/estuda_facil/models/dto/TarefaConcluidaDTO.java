package com.unifor.estuda_facil.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaConcluidaDTO {
    private Long tarefaId;
    private String titulo;
    private LocalDateTime dataConclusao;

    public TarefaConcluidaDTO(Long tarefaId, String titulo, LocalDateTime dataConclusao) {
        this.tarefaId = tarefaId;
        this.titulo = titulo;
        this.dataConclusao = dataConclusao;
    }
}

