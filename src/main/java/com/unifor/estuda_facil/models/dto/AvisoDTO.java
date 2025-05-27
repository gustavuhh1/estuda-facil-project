package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;



@Data
public class AvisoDTO {
    private Long id;

    @NotBlank(message = "Título do aviso é obrigatório")
    private String titulo;

    private String descricao;
    private LocalDateTime dataCriacao;
    private Long turmaId;
}

