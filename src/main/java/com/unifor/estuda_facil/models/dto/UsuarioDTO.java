package com.unifor.estuda_facil.models.dto;

import com.unifor.estuda_facil.models.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private UUID id;
    private String email;
    private Role role;
}
