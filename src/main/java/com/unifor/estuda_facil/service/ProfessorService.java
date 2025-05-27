package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioService usuarioService;

    public Professor criarProfessor(ProfessorDTO dto) {

        Professor p = new Professor();
        p.setNome(dto.getNome());
        p.setDisciplina(dto.getDisciplina());
        p.setTelefone(dto.getTelefoneContato());
        p.setEmail(dto.getEmail());
        p.setSenha(dto.getSenha());
        p.setRole(Role.PROFESSOR);

        usuarioService.prepararUsuario(p);

        return professorRepository.save(p);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(UUID id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
    }

    public Professor atualizarProfessor(UUID id, ProfessorDTO dto) {
        Professor p = buscarPorId(id);
        p.setNome(dto.getNome());
        p.setDisciplina(dto.getDisciplina());
        p.setTelefone(dto.getTelefoneContato());
        return professorRepository.save(p);
    }

    public void deletarProfessor(UUID id) {
        professorRepository.deleteById(id);
    }
}
