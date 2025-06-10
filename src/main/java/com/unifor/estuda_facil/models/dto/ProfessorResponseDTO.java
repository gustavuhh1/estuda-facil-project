package com.unifor.estuda_facil.models.dto;

import com.unifor.estuda_facil.models.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProfessorResponseDTO extends UsuarioDTO {
    private String nome;
    private String disciplina;
    private String telefoneContato;

    public ProfessorResponseDTO(UUID id, String email, Role role, String nome, String disciplina, String telefoneContato) {
        super(id, email, role);
        this.nome = nome;
        this.disciplina = disciplina;
        this.telefoneContato = telefoneContato;
    }
}
