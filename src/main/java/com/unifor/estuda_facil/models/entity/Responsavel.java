package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "responsaveis")
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String telefone;
    private String cpf;

    @ManyToMany(mappedBy = "responsaveis")
    private List<Aluno> alunos;
}