package com.unifor.estuda_facil.models.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private final String token;

    public UsuarioResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
