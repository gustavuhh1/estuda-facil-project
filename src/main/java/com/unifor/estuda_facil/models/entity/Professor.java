package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
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

}
