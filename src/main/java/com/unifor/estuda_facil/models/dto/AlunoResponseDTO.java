package com.unifor.estuda_facil.models.dto;

import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.models.entity.Responsavel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class AlunoResponseDTO {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String matricula;
    private Long turmaId;
    private List<UUID> responsavelIds;
    private String email;

    public AlunoResponseDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.dataNascimento = aluno.getDataNascimento();
        this.matricula = aluno.getMatricula();
        this.email = aluno.getEmail();
        this.turmaId = aluno.getTurma() != null ? aluno.getTurma().getId() : null;
        this.responsavelIds = aluno.getResponsaveis() != null ?
                aluno.getResponsaveis().stream().map(Responsavel::getId).collect(Collectors.toList())
                : List.of();
    }
}
