package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.AgendaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendaEventoRepository extends JpaRepository<AgendaEvento, Long> {
    List<AgendaEvento> findByTurmaIdOrderByDataEventoAsc(Long turmaId);
    List<AgendaEvento> findByProfessorIdOrderByDataEventoAsc(Long professorId);

    @Query("""
    SELECT e FROM AgendaEvento e
    WHERE e.aluno.id = :alunoId
       OR (e.turma.id = :turmaId AND e.aluno IS NULL)
    ORDER BY e.dataEvento ASC
""")
    List<AgendaEvento> buscarAgendaCompletaDoAluno(@Param("alunoId") Long alunoId,
                                                   @Param("turmaId") Long turmaId);

}
