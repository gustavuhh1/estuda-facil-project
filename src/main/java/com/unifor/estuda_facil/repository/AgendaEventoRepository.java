package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.AgendaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaEventoRepository extends JpaRepository<AgendaEvento, Long> {
    List<AgendaEvento> findByTurmaIdOrderByDataEventoAsc(Long turmaId);
    List<AgendaEvento> findByProfessorIdOrderByDataEventoAsc(Long professorId);

}
