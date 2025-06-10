package com.unifor.estuda_facil.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "professores")
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends Usuario {

    @Column(nullable = false)
    private String nome;
    private String disciplina;
    private String telefone;

    @ManyToMany(mappedBy = "professores")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private List<Turma> turmas;

}
