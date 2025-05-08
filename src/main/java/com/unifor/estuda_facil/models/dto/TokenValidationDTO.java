package com.unifor.estuda_facil.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenValidationDTO {
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "Código é obrigatório")
    private String code;
}
