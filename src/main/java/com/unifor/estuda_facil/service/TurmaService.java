package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Loggable
    public List<Turma> listarTodas() {
        return turmaRepository.findAll();

    }
    @Loggable
    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);

    }
    @Loggable
    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);

    }
    @Loggable
    public Turma atualizar(Long id, Turma turmaAtualizada) {
        return turmaRepository.findById(id).map(turma -> {
            turma.setNome(turmaAtualizada.getNome());
            turma.setCodigo(turmaAtualizada.getCodigo());
            turma.setAnoLetivo(turmaAtualizada.getAnoLetivo());
            return turmaRepository.save(turma);
        }).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }
    @Loggable
    public void deletar(Long id) {
        turmaRepository.deleteById(id);

    }
}
