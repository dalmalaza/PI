package com.dh.PI.controller;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Categoria;
import com.dh.PI.model.Imagen;
import com.dh.PI.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    private IImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<Imagen>> listarImagenes(){
        return ResponseEntity.ok(imagenService.listarImagenes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> obtenerImagenXId(@PathVariable Long id){
        Optional<Imagen> imagenBuscada = imagenService.buscarImagenXId(id);
        if(imagenBuscada.isPresent()){
            return ResponseEntity.ok(imagenBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Imagen> agregarImagen(@RequestBody Imagen imagen) throws BadRequestException {
        //chequea que tenga todos los datos completos
        if(Imagen.isImagenCompleta(imagen)){
            return ResponseEntity.ok(imagenService.agregarImagen(imagen));
        }else{
            throw new BadRequestException("Error. Los datos de la imagen deben que estar 100% completos");
        }
    }

    @PutMapping
    public ResponseEntity<Imagen> editarImagen(@RequestBody Imagen imagen){
        Optional<Imagen> imagenAEditar = imagenService.buscarImagenXId(imagen.getId());
        if(imagenAEditar.isPresent()){
            Imagen imagenEditada = imagenService.editarImagen(imagen);
            return ResponseEntity.ok(imagenEditada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Imagen> imagenABorrar = imagenService.buscarImagenXId(id);
        if(imagenABorrar.isPresent()){
            imagenService.eliminarImagen(id);
            return ResponseEntity.ok("Se ha eliminado la imagen con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se encontró la imagen para realizar su eliminación");
        }
    }

}
