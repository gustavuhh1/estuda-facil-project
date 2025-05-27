package com.unifor.estuda_facil.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    private String caminhoArquivo;

    private String tipo;

    private Long tamanho;

    @ManyToOne
    @JoinColumn(name = "tarefa_id")
    @JsonBackReference
    @ToString.Exclude
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "aviso_id")
    @JsonBackReference
    @ToString.Exclude
    private Aviso aviso;
}
