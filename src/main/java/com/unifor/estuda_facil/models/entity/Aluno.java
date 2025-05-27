package com.unifor.estuda_facil.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "alunos")
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario{

    @Column(nullable = false)
    private String nome;

    private LocalDate dataNascimento;
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    @JsonBackReference
    private Turma turma;

    @ManyToMany
    @JoinTable(
            name = "aluno_responsavel",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    private List<Responsavel> responsaveis;
}
