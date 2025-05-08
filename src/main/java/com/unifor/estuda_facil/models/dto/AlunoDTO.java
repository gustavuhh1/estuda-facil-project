package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for Aluno entity.
 */
@Data
public class AlunoDTO {
    private Long id;

    @NotNull(message = "Nome do aluno é obrigatório")
    private String nome;

    private LocalDate dataNascimento;

    private String matricula;

    /** ID da turma à qual o aluno pertence */
    private Long turmaId;

    /** Lista de IDs de responsáveis pelo aluno */
    private List<Long> responsavelIds;
}
