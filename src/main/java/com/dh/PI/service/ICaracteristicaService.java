package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Caracteristica;

import java.util.List;
import java.util.Optional;

public interface ICaracteristicaService {
    List<Caracteristica> listarCaracteristicas();
    Optional<Caracteristica> buscarCaracteristicaXId(Long id);
    Optional<Caracteristica> buscarCaracteristicaXNombre(String nombre);
    Caracteristica agregarCaracteristica(Caracteristica caracteristica) throws BadRequestException;
    Caracteristica editarCaracteristica(Caracteristica caracteristica);
    void eliminarCaracteristica(Long id);

}



