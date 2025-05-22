package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.entity.Tarefa;
import com.unifor.estuda_facil.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Loggable
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();

    }
    @Loggable
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);

    }
    @Loggable
    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);

    }
    @Loggable
    public void deletar(Long id) {
        tarefaRepository.deleteById(id);

    }
}
