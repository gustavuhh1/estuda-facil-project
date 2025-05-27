package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByDestinatarioOrderByDataEnvioDesc(Usuario destinatario);
    long countByDestinatarioIdAndLidaFalse(UUID destinatarioId);
    @Query("""
    SELECT m FROM Mensagem m
    WHERE 
        (m.remetente.id = :usuario1 AND m.destinatario.id = :usuario2)
        OR 
        (m.remetente.id = :usuario2 AND m.destinatario.id = :usuario1)
    ORDER BY m.dataEnvio
""")
    List<Mensagem> buscarConversaEntre(UUID usuario1, UUID usuario2);
}

