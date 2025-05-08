package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.models.dto.AlunoDTO;
import com.unifor.estuda_facil.models.entity.Aluno;
import com.unifor.estuda_facil.models.entity.Turma;
import com.unifor.estuda_facil.models.entity.Responsavel;
import com.unifor.estuda_facil.repository.AlunoRepository;
import com.unifor.estuda_facil.repository.TurmaRepository;
import com.unifor.estuda_facil.repository.ResponsavelRepository;
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

    public Aluno criarAluno(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        if (dto.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
            aluno.setTurma(turma);
        }

        if (dto.getResponsavelIds() != null && !dto.getResponsavelIds().isEmpty()) {
            List<Responsavel> responsaveis = dto.getResponsavelIds().stream()
                    .map(id -> responsavelRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado: " + id)))
                    .collect(Collectors.toList());
            aluno.setResponsaveis(responsaveis);
        }

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    public Aluno atualizarAluno(Long id, AlunoDTO dto) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setMatricula(dto.getMatricula());

        if (dto.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
            aluno.setTurma(turma);
        }

        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
