package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.OptionalDouble;
import java.util.UUID;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {
}
