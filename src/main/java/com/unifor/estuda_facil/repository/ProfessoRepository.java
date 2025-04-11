package com.unifor.estuda_facil.repository;

import com.unifor.estuda_facil.models.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessoRepository extends JpaRepository<Professor, UUID> {
}
