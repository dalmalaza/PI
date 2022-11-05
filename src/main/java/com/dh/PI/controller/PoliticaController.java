package com.dh.PI.controller;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Politica;
import com.dh.PI.service.IPoliticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/politicas")
public class PoliticaController {

    @Autowired
    private IPoliticaService politicaService;

    @GetMapping
    public ResponseEntity<List<Politica>> listarPoliticas(){
        return ResponseEntity.ok(politicaService.listarPoliticas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Politica> obtenerPoliticaXId(@PathVariable Long id){
        Optional<Politica> politicaBuscada = politicaService.buscarPoliticaXId(id);
        if(politicaBuscada.isPresent()){
            return ResponseEntity.ok(politicaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Politica> buscarPoliticaXTitulo(@PathVariable String titulo){
        Optional<Politica> politicaBuscada = politicaService.buscarPoliticaXTitulo(titulo);
        if(politicaBuscada.isPresent()){
            return ResponseEntity.ok(politicaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Politica> agregarPolitica(@RequestBody Politica politica) throws BadRequestException {
        //chequea que tenga todos los datos completos
        if(Politica.isPoliticaCompleta(politica)){
            return ResponseEntity.ok(politicaService.agregarPolitica(politica));
        }else{
            throw new BadRequestException("Error. Los datos de la politica deben que estar 100% completos");
        }
    }

    @PutMapping
    public ResponseEntity<Politica> editarPolitica(@RequestBody Politica politica){
        Optional<Politica> politicaAEditar = politicaService.buscarPoliticaXId(politica.getId());
        if(politicaAEditar.isPresent()){
            Politica politicaEditada = politicaService.editarPolitica(politica);
            return ResponseEntity.ok(politicaEditada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPolitica(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Politica> politicaABorrar = politicaService.buscarPoliticaXId(id);
        if(politicaABorrar.isPresent()){
            politicaService.eliminarPolitica(id);
            return ResponseEntity.ok("Se ha eliminado la política con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se encontró la política para realizar su eliminación");
        }
    }

}
