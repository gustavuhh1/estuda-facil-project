package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma atualizar(Long id, Turma turmaAtualizada) {
        return turmaRepository.findById(id).map(turma -> {
            turma.setNome(turmaAtualizada.getNome());
            turma.setCodigo(turmaAtualizada.getCodigo());
            turma.setAnoLetivo(turmaAtualizada.getAnoLetivo());
            return turmaRepository.save(turma);
        }).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public void deletar(Long id) {
        turmaRepository.deleteById(id);
    }
    // adicione ProfessorRepository como dependência
    @Autowired
    private ProfessorRepository professorRepository;

    public Turma adicionarProfessor(Long turmaId, UUID professorId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        // Evita duplicados
        if (!turma.getProfessores().contains(professor)) {
            turma.getProfessores().add(professor);
            turma = turmaRepository.save(turma);
        }

        return turma;
    }

}
