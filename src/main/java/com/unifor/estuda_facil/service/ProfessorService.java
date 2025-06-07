package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.ProfessorDTO;
import com.unifor.estuda_facil.models.dto.TurmaDTO;
import com.unifor.estuda_facil.models.entity.Professor;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.ProfessorRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioService usuarioService;
    private final TurmaRepository turmaRepository;

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

    public List<TurmaDTO> turmasDoProfessor(UUID professorId) {
        return turmaRepository.findAll().stream()
                .filter(t -> t.getProfessores() != null &&
                        t.getProfessores().stream().anyMatch(p -> p.getId().equals(professorId)))
                .map(t -> new TurmaDTO(t.getId(), t.getCodigo(), t.getNome(), t.getAnoLetivo()))
                .collect(Collectors.toList());
    }
}
