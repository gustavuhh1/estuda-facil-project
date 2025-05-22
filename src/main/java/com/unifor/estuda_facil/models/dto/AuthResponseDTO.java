package com.unifor.estuda_facil.models.dto;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
    private final String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}

