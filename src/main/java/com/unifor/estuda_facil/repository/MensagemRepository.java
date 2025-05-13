package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Mensagem;
import com.unifor.estuda_facil.models.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByDestinatarioOrderByDataEnvioDesc(Responsavel responsavel);
}
