package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

import java.util.UUID;

@Data
public class AvisoDTO {
    private UUID id;

    @NotBlank(message = "Título do aviso é obrigatório")
    private String titulo;

    private String descricao;
    private LocalDateTime dataCriacao;
    private UUID turmaId;
}

