package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    private String nome;
    private String anoLetivo;

    @OneToMany(mappedBy = "turma")
    private List<Estudante> estudantes;
}