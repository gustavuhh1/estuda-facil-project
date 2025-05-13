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
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor remetente;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Responsavel destinatario;

    private String conteudo;

    private LocalDateTime dataEnvio;

    private boolean lida = false;
}
