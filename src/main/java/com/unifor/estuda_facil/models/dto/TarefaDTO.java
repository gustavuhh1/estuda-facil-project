package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaDTO {
    private Long id;

    @NotBlank(message = "Título da tarefa é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data de entrega é obrigatória")
    private LocalDate dataEntrega;


    private Long turmaId;
}
