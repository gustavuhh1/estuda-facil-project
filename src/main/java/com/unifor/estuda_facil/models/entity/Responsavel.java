package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "responsaveis")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel extends Usuario{


    @Column(nullable = false)
    private String nome;

    private String telefone;
    private String cpf;

    @ManyToMany(mappedBy = "responsaveis")
    private List<Aluno> alunos;
}