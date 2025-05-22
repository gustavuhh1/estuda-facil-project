// src/main/java/com/unifor/estuda_facil/repository/TokenRepository.java
package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByEmailAndCode(String email, String code);
}
