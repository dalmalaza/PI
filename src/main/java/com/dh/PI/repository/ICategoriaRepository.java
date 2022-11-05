package com.dh.PI.repository;

import com.dh.PI.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTitulo(String titulo);
}
