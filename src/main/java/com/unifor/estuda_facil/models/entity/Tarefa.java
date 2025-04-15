package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime dataEntrega;

    private String status; // Pode ser: PENDENTE, ENTREGUE, ATRASADA, etc.

    private Double nota; // Pode ser null até ser corrigida

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
}
