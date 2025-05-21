package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.AgendaEventoDTO;
import com.unifor.estuda_facil.models.entity.*;

import org.springframework.stereotype.Component;

@Component
public class AgendaEventoFactory {

    public AgendaEvento criar(AgendaEventoDTO dto, Professor professor, Turma turma, Aluno aluno) {
        AgendaEvento evento = new AgendaEvento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setTipo(dto.getTipo());
        evento.setProfessor(professor);
        evento.setTurma(turma);
        evento.setAluno(aluno); // Pode ser null
        return evento;
    }

    public AgendaEvento criar(AgendaEventoDTO dto, Professor professor, Turma turma) {
        return criar(dto, professor, turma, null);
    }

    public void atualizar(AgendaEvento evento, AgendaEventoDTO dto, Professor professor, Turma turma) {
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setTipo(dto.getTipo());
        evento.setProfessor(professor);
        evento.setTurma(turma);
    }
}
