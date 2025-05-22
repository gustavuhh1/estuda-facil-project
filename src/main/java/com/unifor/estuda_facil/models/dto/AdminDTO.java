package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminDTO {
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    private String departamento;
    private String telefoneContato;

    @Email(message = "Email inválido!")
    @NotBlank(message = "Email é obrigatório!")
    private String email;

    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres!")
    @NotBlank(message = "Senha é obrigatória!")
    private String senha;
}
