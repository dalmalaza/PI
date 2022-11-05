package com.dh.PI.service;

import com.dh.PI.model.Imagen;
import com.dh.PI.repository.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ImagenServiceImpl implements IImagenService{
    @Autowired
    private IImagenRepository imagenRepository;

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepository.findAll();
    }

    @Override
    public Optional<Imagen> buscarImagenXId(Long id) {
        return imagenRepository.findById(id);
    }

    @Override
    public  Imagen agregarImagen(Imagen imagen) {
            return imagenRepository.save(imagen);
    }

    @Override
    public Imagen editarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    @Override
    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }

}
