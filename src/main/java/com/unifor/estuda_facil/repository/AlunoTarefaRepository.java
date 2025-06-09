package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.AlunoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoTarefaRepository extends JpaRepository<AlunoTarefa, UUID> {

    Optional<AlunoTarefa> findByAlunoIdAndTarefaId(UUID alunoId, Long tarefaId);

    List<AlunoTarefa> findByAlunoId(UUID alunoId);

    List<AlunoTarefa> findByAlunoIdAndConcluidaTrue(UUID alunoId);

    List<AlunoTarefa> findByTarefaIdAndConcluidaTrue(Long tarefaId);

    List<AlunoTarefa> findByTarefaId(Long tarefaId);
}
