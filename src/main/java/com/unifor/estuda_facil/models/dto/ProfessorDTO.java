package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfessorDTO implements UsuarioResponseDTO{
    private UUID id;

    @NotBlank(message = "Nome do professor é obrigatório")
    private String nome;

    private String disciplina;
    private String telefoneContato;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    private String Role;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}
