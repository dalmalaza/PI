package com.dh.PI.controller;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Categoria;
import com.dh.PI.model.Ciudad;
import com.dh.PI.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {
    @Autowired
    private ICiudadService ciudadService;

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarCiudades(){
        return ResponseEntity.ok(ciudadService.listarCiudades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> obtenerCiudadXId(@PathVariable Long id){
        Optional<Ciudad> ciudadaBuscada = ciudadService.buscarCiudadXId(id);
        if(ciudadaBuscada.isPresent()){
            return ResponseEntity.ok(ciudadaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Ciudad> buscarCiudadXNombre(@PathVariable String nombre){
        Optional<Ciudad> ciudadaBuscada = ciudadService.buscarCiudadXNombre(nombre);
        if(ciudadaBuscada.isPresent()){
            return ResponseEntity.ok(ciudadaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Ciudad> agregarCiudad(@RequestBody Ciudad ciudad) throws BadRequestException {
        //chequea que tenga todos los datos completos
        if(Ciudad.isCiudadCompleta(ciudad)){
            return ResponseEntity.ok(ciudadService.agregarCiudad(ciudad));
        }else{
            throw new BadRequestException("Error. Los datos de la ciudad deben que estar 100% completos");
        }
    }

    @PutMapping
    public ResponseEntity<Ciudad> editarCiudad(@RequestBody Ciudad ciudad){
        Optional<Ciudad> ciudadAEditar = ciudadService.buscarCiudadXId(ciudad.getId());
        if(ciudadAEditar.isPresent()){
            Ciudad ciudadEditada = ciudadService.editarCiudad(ciudad);
            return ResponseEntity.ok(ciudadEditada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Ciudad> ciudadABorrar = ciudadService.buscarCiudadXId(id);
        if(ciudadABorrar.isPresent()){
            ciudadService.eliminarCiudad(id);
            return ResponseEntity.ok("Se ha eliminado la ciudad con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se encontró la ciudad para realizar su eliminación");
        }
    }

}
