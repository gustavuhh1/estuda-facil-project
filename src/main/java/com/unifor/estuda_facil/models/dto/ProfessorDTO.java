package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfessorDTO implements UsuarioResponseDTO {
    private UUID id;

    private String nome;
    private String disciplina;
    private String telefoneContato;

    @Email(message = "Email inválido")
    private String email;

    private String role;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;
}
