package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> listarCategorias();
    Optional<Categoria> buscarCategoriaXId(Long id);
    Optional<Categoria> buscarCategoriaXTitulo(String titulo);
    Categoria agregarCategoria(Categoria categoria) throws BadRequestException;
    Categoria editarCategoria(Categoria categoria);
    void eliminarCategoria(Long id);

}
