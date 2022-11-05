package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Ciudad;

import java.util.List;
import java.util.Optional;

public interface ICiudadService {
    List<Ciudad> listarCiudades();
    Optional<Ciudad> buscarCiudadXId(Long id);
    Optional<Ciudad> buscarCiudadXNombre(String nombre);
    Ciudad agregarCiudad(Ciudad ciudad) throws BadRequestException;
    Ciudad editarCiudad(Ciudad ciudad);
    void eliminarCiudad(Long id);
}

