package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisoRepository extends JpaRepository<Aviso, Long> {
    List<Aviso> findByTurmaIdOrTurmaIsNullOrderByDataCriacaoDesc(Long turmaId);

}
