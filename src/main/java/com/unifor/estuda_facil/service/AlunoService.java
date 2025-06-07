package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.dto.AlunoResponseDTO;
import com.unifor.estuda_facil.models.entity.*;
import com.unifor.estuda_facil.models.entity.enums.Role;
import com.unifor.estuda_facil.repository.AlunoRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioService usuarioService;
    private final TurmaRepository turmaRepository;

    @Loggable
    public Aluno criarAluno(AlunoResponseDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha());
        aluno.setRole(Role.ALUNO);

        usuarioService.prepararUsuario(aluno);

        // Associa turma (se informada)
        buscarTurma(dto.getTurmaId()).ifPresent(aluno::setTurma);

        return alunoRepository.save(aluno);
    }

    @Loggable
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(UUID id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    @Loggable
    public Aluno atualizarAluno(UUID id, AlunoDTO dto) {
        Aluno aluno = buscarPorId(id);

        if (dto.getNome() != null) {
            aluno.setNome(dto.getNome());
        }
        if (dto.getDataNascimento() != null) {
            aluno.setDataNascimento(dto.getDataNascimento());
        }
        if (dto.getMatricula() != null) {
            aluno.setMatricula(dto.getMatricula());
        }
        if (dto.getEmail() != null) {
            aluno.setEmail(dto.getEmail());
        }
        if (dto.getTurmaId() != null) {
            buscarTurma(dto.getTurmaId()).ifPresent(aluno::setTurma);
        }

        return alunoRepository.save(aluno);
    }

    @Loggable
    public void deletarAluno(UUID id) {
        alunoRepository.deleteById(id);
    }

    public void atribuirTurma(UUID alunoId, Long turmaId) {
        Aluno aluno = buscarPorId(alunoId);
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        aluno.setTurma(turma);
        alunoRepository.save(aluno);
    }

    // 🔽 Métodos auxiliares clean 🔽

    private java.util.Optional<Turma> buscarTurma(Long turmaId) {
        if (turmaId == null) return java.util.Optional.empty();
        return turmaRepository.findById(turmaId);
    }
}
