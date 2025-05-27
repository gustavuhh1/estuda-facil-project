package com.unifor.estuda_facil.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


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
    @ToString.Exclude
    @JsonBackReference
    private Turma turma;

    @ManyToMany
    @JoinTable(
            name = "aluno_responsavel",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    @ToString.Exclude
    private List<Responsavel> responsaveis;
}
