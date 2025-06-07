package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for Aluno entity.
 */
@Data
public class AlunoDTO implements UsuarioResponseDTO{
    private UUID id;

    @Email
    private String email;
    private String role;
    private String nome;
    private String senha;
    private LocalDate dataNascimento;
    private String matricula;
    private Long turmaId;
}
