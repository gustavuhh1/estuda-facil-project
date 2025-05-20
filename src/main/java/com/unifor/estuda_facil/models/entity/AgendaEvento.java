package com.unifor.estuda_facil.models.entity;

import com.unifor.estuda_facil.models.entity.enums.TipoEvento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDate dataEvento;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
