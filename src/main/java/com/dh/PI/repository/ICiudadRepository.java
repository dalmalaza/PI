package com.dh.PI.repository;

import com.dh.PI.model.Caracteristica;
import com.dh.PI.model.Categoria;
import com.dh.PI.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICiudadRepository extends JpaRepository<Ciudad, Long> {
    Optional<Ciudad> findByNombre(String nombre);
}
