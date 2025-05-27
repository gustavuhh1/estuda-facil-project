package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Aluno entity.
 */
@Data
public class AlunoDTO {

    @NotNull(message = "Nome do aluno é obrigatório")
    private String nome;

    private LocalDate dataNascimento;
    private String matricula;

    private Long turmaId;
    private List<UUID> responsavelIds;

    @Email(message = "Email inválido")
    @NotNull(message = "Email é obrigatório")
    private String email;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @NotNull(message = "Senha é obrigatória")
    private String senha;
}
