package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.entity.Aviso;
import com.unifor.estuda_facil.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public List<Aviso> listarTodos() {
        return avisoRepository.findAll();

    }

    public Optional<Aviso> buscarPorId(Long id) {
        return avisoRepository.findById(id);

    }

    public Aviso salvar(Aviso aviso) {
        return avisoRepository.save(aviso);

    }

    public void deletar(Long id) {
        avisoRepository.deleteById(id);

    }
    public List<Aviso> listarPorTurmaOuGerais(Long turmaId) {
        return avisoRepository.findByTurmaIdOrTurmaIsNullOrderByDataCriacaoDesc(turmaId);
    }

    public List<Aviso> listarTodosOrdenado() {
        List<Aviso> avisos = avisoRepository.findAll();
        avisos.sort((a, b) -> b.getDataCriacao().compareTo(a.getDataCriacao())); // ordem decrescente
        return avisos;
    }

}
