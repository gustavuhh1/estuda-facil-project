package com.unifor.estuda_facil.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenResponseDTO {
    private String code;
    private LocalDateTime expiresAt;
}
