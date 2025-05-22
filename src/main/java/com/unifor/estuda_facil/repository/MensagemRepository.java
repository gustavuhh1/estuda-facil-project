package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByDestinatarioOrderByDataEnvioDesc(Responsavel responsavel);
    long countByDestinatarioIdAndLidaFalse(Long destinatarioId);
    @Query("SELECT m FROM Mensagem m " +
            "WHERE (m.remetente.id = :professorId AND m.destinatario.id = :responsavelId) " +
            "   OR (m.remetente.id = :responsavelId AND m.destinatario.id = :professorId) " +
            "ORDER BY m.dataEnvio ASC")
    List<Mensagem> buscarConversaEntre(@Param("professorId") Long professorId,
                                       @Param("responsavelId") Long responsavelId);

}

