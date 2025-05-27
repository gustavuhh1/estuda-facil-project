package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "admins")
@AllArgsConstructor // Adiciona um construtor com todos os campos
@NoArgsConstructor  // Adiciona um construtor sem argumentos
public class Admin extends Usuario{

    @Column(nullable = false)
    private String nome;

    private String departamento;
    private String telefoneContato;
}
