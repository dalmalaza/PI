package com.dh.PI.service;

import com.dh.PI.model.Politica;

import java.util.List;
import java.util.Optional;

public interface IPoliticaService {
    List<Politica> listarPoliticas();
    Optional<Politica> buscarPoliticaXId(Long id);
    Optional<Politica> buscarPoliticaXTitulo(String titulo);
    Politica agregarPolitica(Politica politica);
    Politica editarPolitica(Politica politica);
    void eliminarPolitica(Long id);

}
