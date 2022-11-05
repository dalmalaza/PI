package com.dh.PI.repository;

import com.dh.PI.model.Ciudad;
import com.dh.PI.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByTitulo(String titulo);
    List<Producto> listarProductosXCiudad (Ciudad ciudad);
}
