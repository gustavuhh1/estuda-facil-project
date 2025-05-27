package com.unifor.estuda_facil.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor remetente;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Responsavel destinatario;

    @ManyToOne
    @JoinColumn(name = "resposta_para_id")
    private Mensagem respostaPara;


    private String conteudo;

    private LocalDateTime dataEnvio;

    private boolean lida = false;
}
