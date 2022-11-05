package com.dh.PI.controller;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Caracteristica;
import com.dh.PI.service.ICaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {
    @Autowired
    private ICaracteristicaService caracteristicaService;

    @GetMapping
    public ResponseEntity<List<Caracteristica>> listarCaracteristicas(){
        return ResponseEntity.ok(caracteristicaService.listarCaracteristicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caracteristica> obtenerCaracteristicaXId(@PathVariable Long id){
        Optional<Caracteristica> caracteristicaBuscada = caracteristicaService.buscarCaracteristicaXId(id);
        if(caracteristicaBuscada.isPresent()){
            return ResponseEntity.ok(caracteristicaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Caracteristica> buscarCaracteristicaXNombre(@PathVariable String nombre){
        Optional<Caracteristica> caracteristicaBuscada = caracteristicaService.buscarCaracteristicaXNombre(nombre);
        if(caracteristicaBuscada.isPresent()){
            return ResponseEntity.ok(caracteristicaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Caracteristica> agregarCaracteristica(@RequestBody Caracteristica caracteristica) throws BadRequestException {
        //chequea que tenga todos los datos completos
        if(Caracteristica.isCaracteristicaCompleta(caracteristica)){
            return ResponseEntity.ok(caracteristicaService.agregarCaracteristica(caracteristica));
        }else{
            throw new BadRequestException("Error. Los datos de la característica deben que estar 100% completos");
        }
    }

    @PutMapping
    public ResponseEntity<Caracteristica> editarCaracteristica(@RequestBody Caracteristica caracteristica){
        Optional<Caracteristica> caracteristicaAEditar = caracteristicaService.buscarCaracteristicaXId(caracteristica.getId());
        if(caracteristicaAEditar.isPresent()){
            Caracteristica caracteristicaEditada = caracteristicaService.editarCaracteristica(caracteristica);
            return ResponseEntity.ok(caracteristicaEditada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaracteristica(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Caracteristica> caracteristicaABorrar = caracteristicaService.buscarCaracteristicaXId(id);
        if(caracteristicaABorrar.isPresent()){
            caracteristicaService.eliminarCaracteristica(id);
            return ResponseEntity.ok("Se ha eliminado la característica con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se encontró la característica para realizar su eliminación");
        }
    }
}
