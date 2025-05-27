package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.AgendaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AgendaEventoRepository extends JpaRepository<AgendaEvento, Long> {
    List<AgendaEvento> findByTurmaIdOrderByDataEventoAsc(Long turmaId);
    List<AgendaEvento> findByProfessorIdOrderByDataEventoAsc(UUID professorId);

    @Query("""
    SELECT e FROM AgendaEvento e
    WHERE e.aluno.id = :alunoId
       OR (e.turma.id = :turmaId AND e.aluno IS NULL)
    ORDER BY e.dataEvento ASC
""")
    List<AgendaEvento> buscarAgendaCompletaDoAluno(@Param("alunoId") UUID alunoId,
                                                   @Param("turmaId") Long turmaId);

}
