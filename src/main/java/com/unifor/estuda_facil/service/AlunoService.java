package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.factory.UsuarioFactory;
import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.entity.*;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.AlunoRepository;
import com.unifor.estuda_facil.repository.ResponsavelRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final ResponsavelRepository responsavelRepository;
    private final UsuarioFactory usuarioFactory;

    @Loggable
    public Aluno criarAluno(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        // Cria e associa o usuário
        Usuario usuario = usuarioFactory.criar(dto.getEmail(), dto.getSenha(), Role.ALUNO);
        aluno.setUsuario(usuario);

        // Associa turma (se informada)
        buscarTurma(dto.getTurmaId()).ifPresent(aluno::setTurma);

        // Associa responsáveis (se houver)
        if (dto.getResponsavelIds() != null && !dto.getResponsavelIds().isEmpty()) {
            aluno.setResponsaveis(buscarResponsaveis(dto.getResponsavelIds()));
        }

        return alunoRepository.save(aluno);
    }

    @Loggable
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    @Loggable
    public Aluno atualizarAluno(Long id, AlunoDTO dto) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        buscarTurma(dto.getTurmaId()).ifPresent(aluno::setTurma);

        return alunoRepository.save(aluno);
    }

    @Loggable
    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    public void atribuirTurma(Long alunoId, Long turmaId) {
        Aluno aluno = buscarPorId(alunoId);
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        aluno.setTurma(turma);
        alunoRepository.save(aluno);
    }

    // 🔽 Métodos auxiliares clean 🔽

    private List<Responsavel> buscarResponsaveis(List<Long> ids) {
        return ids.stream()
                .map(id -> responsavelRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado: " + id)))
                .collect(Collectors.toList());
    }

    private java.util.Optional<Turma> buscarTurma(Long turmaId) {
        if (turmaId == null) return java.util.Optional.empty();
        return turmaRepository.findById(turmaId);
    }
}
