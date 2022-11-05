package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Ciudad;
import com.dh.PI.repository.ICiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements ICiudadService {
    @Autowired
    private ICiudadRepository ciudadRepository;

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepository.findAll();
    }

    @Override
    public Optional<Ciudad> buscarCiudadXId(Long id) {
        return ciudadRepository.findById(id);
    }

    @Override
    public Optional<Ciudad> buscarCiudadXNombre(String nombre) {
        return ciudadRepository.findByNombre(nombre);
    }

    @Override
    public Ciudad agregarCiudad(Ciudad ciudad) throws BadRequestException {
        Optional<Ciudad> ciudadXNombre = ciudadRepository.findByNombre(ciudad.getNombre());
        if(ciudadXNombre.isPresent()){
            throw new BadRequestException("Ya existe una ciudad con ese nombre");
        }else{
            return ciudadRepository.save(ciudad);
        }
    }

    @Override
    public Ciudad editarCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
}
