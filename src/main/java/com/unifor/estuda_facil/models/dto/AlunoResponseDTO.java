package com.unifor.estuda_facil.models.dto;

import com.unifor.estuda_facil.models.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AlunoResponseDTO {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String matricula;
    private Long turmaId;
    private String email;
    private String senha;

}
