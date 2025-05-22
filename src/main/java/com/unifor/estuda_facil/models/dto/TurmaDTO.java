package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TurmaDTO {
    private Long id;

    @NotBlank(message = "Código da turma é obrigatório")
    private String codigo;

    @NotBlank(message = "Nome da turma é obrigatório")
    private String nome;

    private String anoLetivo;
}
