package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessorDTO {
    private Long id;

    @NotBlank(message = "Nome do professor é obrigatório")
    private String nome;

    private String disciplina;
    private String telefone;
}
