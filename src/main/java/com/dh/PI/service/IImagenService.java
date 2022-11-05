package com.dh.PI.service;

import com.dh.PI.model.Imagen;

import java.util.List;
import java.util.Optional;

public interface IImagenService {
    List<Imagen> listarImagenes();
    Optional<Imagen> buscarImagenXId(Long id);
    Imagen agregarImagen(Imagen imagen) ;
    Imagen editarImagen(Imagen imagen);
    void eliminarImagen(Long id);

}

