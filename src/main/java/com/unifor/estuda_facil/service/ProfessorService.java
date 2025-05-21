// src/main/java/com/unifor/estuda_facil/service/ProfessorService.java
package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Loggable
    public Professor criarProfessor(ProfessorDTO dto) {
        Professor p = new Professor();
        p.setNome(dto.getNome());
        p.setDisciplina(dto.getDisciplina());
        p.setTelefone(dto.getTelefone());
        return professorRepository.save(p);
    }
    @Loggable
    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
    }
    @Loggable
    public Professor atualizarProfessor(Long id, ProfessorDTO dto) {
        Professor p = buscarPorId(id);
        p.setNome(dto.getNome());
        p.setDisciplina(dto.getDisciplina());
        p.setTelefone(dto.getTelefone());
        return professorRepository.save(p);
    }
    @Loggable
    public void deletarProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}
