package com.unifor.estuda_facil.factory;

import com.unifor.estuda_facil.models.dto.TarefaDTO;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.models.entity.Turma;

public class TarefaFactory {

    public static Tarefa criarTarefa(TarefaDTO dto, Turma turma) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataEntrega(dto.getDataEntrega());
        tarefa.setTurma(turma);
        return tarefa;
    }

    public static Tarefa atualizarTarefa(Tarefa tarefa, TarefaDTO dto, Turma turma) {
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataEntrega(dto.getDataEntrega());
        tarefa.setTurma(turma);
        return tarefa;
    }
}
