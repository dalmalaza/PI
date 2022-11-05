package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Caracteristica;
import com.dh.PI.repository.ICaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaServiceImpl implements ICaracteristicaService {
    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;

    @Override
    public List<Caracteristica> listarCaracteristicas() {
        return caracteristicaRepository.findAll();
    }

    @Override
    public Optional<Caracteristica> buscarCaracteristicaXId(Long id) {
        return caracteristicaRepository.findById(id);
    }

    @Override
    public Optional<Caracteristica> buscarCaracteristicaXNombre(String nombre) {
        return caracteristicaRepository.findByNombre(nombre);
    }

    @Override
    public Caracteristica agregarCaracteristica(Caracteristica caracteristica) throws BadRequestException {
        Optional<Caracteristica> caracteristicaXNombre = caracteristicaRepository.findByNombre(caracteristica.getNombre());
        if(caracteristicaXNombre.isPresent()){
            throw new BadRequestException("Ya existe una caracteristica con ese nombre");
        }else{
            return caracteristicaRepository.save(caracteristica);
        }
    }

    @Override
    public Caracteristica editarCaracteristica(Caracteristica caracteristica) {
        return caracteristicaRepository.save(caracteristica);
    }

    @Override
    public void eliminarCaracteristica(Long id) {
        caracteristicaRepository.deleteById(id);
    }

}
