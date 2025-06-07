package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TurmaDTO {
    private Long id;

    @NotBlank(message = "Código da turma é obrigatório")
    private String codigo;

    @NotBlank(message = "Nome da turma é obrigatório")
    private String nome;

    private String anoLetivo;
}

