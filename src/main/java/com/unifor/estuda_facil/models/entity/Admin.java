package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Entity
@Table(name = "admins")
@AllArgsConstructor // Adiciona um construtor com todos os campos
@NoArgsConstructor  // Adiciona um construtor sem argumentos
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String departamento;
    private String telefoneContato;
}
