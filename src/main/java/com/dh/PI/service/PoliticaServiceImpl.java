package com.dh.PI.service;

import com.dh.PI.model.Politica;
import com.dh.PI.repository.IPoliticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliticaServiceImpl implements IPoliticaService{
    @Autowired
    private IPoliticaRepository politicaRepository;

    @Override
    public List<Politica> listarPoliticas() {
        return politicaRepository.findAll();
    }

    @Override
    public Optional<Politica> buscarPoliticaXId(Long id) {
        return politicaRepository.findById(id);
    }

    @Override
    public Optional<Politica> buscarPoliticaXTitulo(String titulo) {
        return politicaRepository.findByTitulo(titulo);
    }

    @Override
    public Politica agregarPolitica(Politica politica) {
        return politicaRepository.save(politica);
    }

    @Override
    public Politica editarPolitica(Politica politica) {
        return politicaRepository.save(politica);
    }

    @Override
    public void eliminarPolitica(Long id) {
        politicaRepository.deleteById(id);
    }
}
