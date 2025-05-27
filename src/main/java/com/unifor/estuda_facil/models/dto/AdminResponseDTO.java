package com.unifor.estuda_facil.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@EqualsAndHashCode(callSuper=false)
public class AdminResponseDTO implements UsuarioResponseDTO {
    private UUID id;
    private String email;
    private String Role;
    private String nome;
    private String departamento;
    private String telefoneContato;

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getRole() {
        return "";
    }
}
