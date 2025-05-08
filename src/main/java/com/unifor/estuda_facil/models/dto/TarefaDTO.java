package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarefaDTO {
    private Long id;

    @NotBlank(message = "Título da tarefa é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data de entrega é obrigatória")
    private LocalDateTime dataEntrega;


    private Long turmaId;
}
