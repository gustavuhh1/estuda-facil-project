package com.unifor.estuda_facil.models.dto;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
    private final String token;
    private final UsuarioResponseDTO usuario;

    public AuthResponseDTO(String token, UsuarioResponseDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }
}

