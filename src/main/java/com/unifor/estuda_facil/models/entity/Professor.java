package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String disciplina;
    private String telefone;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
