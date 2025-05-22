package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
