package com.unifor.estuda_facil.models.dto;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
    private final String token;
    private final UsuarioDTO usuario;

    public AuthResponseDTO(String token, UsuarioDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }
}

