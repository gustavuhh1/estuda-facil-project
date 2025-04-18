package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    List<Anexo> findByTarefaId(Long tarefaId);

    List<Anexo> findByAvisoId(Long avisoId);

}
