package com.unifor.estuda_facil.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turma_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    private String nome;
    private String anoLetivo;

    @OneToMany(mappedBy = "turma")
    @JsonManagedReference
    @ToString.Exclude
    private List<Aluno> alunos;

    @ManyToMany
    @JoinTable(
            name = "turma_professor",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;


}
