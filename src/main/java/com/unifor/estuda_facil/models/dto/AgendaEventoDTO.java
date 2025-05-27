package com.unifor.estuda_facil.models.dto;


import com.unifor.estuda_facil.models.entity.enums.TipoEvento;
import lombok.Data;

import java.time.LocalDate;

import java.util.UUID;

@Data
public class AgendaEventoDTO {
    private String titulo;
    private String descricao;
    private LocalDate dataEvento;
    private TipoEvento tipo;
    private UUID turmaId;
    private UUID professorId;
}
