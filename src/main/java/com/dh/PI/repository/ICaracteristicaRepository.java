package com.dh.PI.repository;

import com.dh.PI.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
    Optional<Caracteristica> findByNombre(String nombre);
}
