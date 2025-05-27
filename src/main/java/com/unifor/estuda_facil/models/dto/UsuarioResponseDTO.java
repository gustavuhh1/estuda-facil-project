package com.unifor.estuda_facil.models.dto;

import java.util.UUID;

public interface UsuarioResponseDTO {
    UUID getId();
    String getEmail();
    String getRole();
}
