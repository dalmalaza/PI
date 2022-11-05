package com.dh.PI.repository;

import com.dh.PI.model.Politica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPoliticaRepository extends JpaRepository<Politica, Long> {
    Optional<Politica> findByTitulo(String titulo);
}
