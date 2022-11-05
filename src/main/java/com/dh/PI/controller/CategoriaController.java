package com.dh.PI.controller;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Categoria;
import com.dh.PI.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaXId(@PathVariable Long id){
        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoriaXId(id);
        if(categoriaBuscada.isPresent()){
            return ResponseEntity.ok(categoriaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Categoria> buscarCategoriaXTitulo(@PathVariable String titulo){
        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoriaXTitulo(titulo);
        if(categoriaBuscada.isPresent()){
            return ResponseEntity.ok(categoriaBuscada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> agregarCategoria(@RequestBody Categoria categoria) throws BadRequestException {
        //chequea que tenga todos los datos completos
        if(Categoria.isCategoriaCompleta(categoria)){
            return ResponseEntity.ok(categoriaService.agregarCategoria(categoria));
        }else{
            throw new BadRequestException("Error. Los datos de la categoria deben que estar 100% completos");
        }
    }

    @PutMapping
    public ResponseEntity<Categoria> editarCategoria(@RequestBody Categoria categoria){
        Optional<Categoria> categoriaAEditar = categoriaService.buscarCategoriaXId(categoria.getId());
        if(categoriaAEditar.isPresent()){
            Categoria categoriaEditada = categoriaService.editarCategoria(categoria);
            return ResponseEntity.ok(categoriaEditada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Categoria> categoriaABorrar = categoriaService.buscarCategoriaXId(id);
        if(categoriaABorrar.isPresent()){
            categoriaService.eliminarCategoria(id);
            return ResponseEntity.ok("Se ha eliminado la categoria con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se encontró la categoria para realizar su eliminación");
        }
    }
}
