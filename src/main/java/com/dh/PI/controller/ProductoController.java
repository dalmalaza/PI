package com.dh.PI.controller;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.Ciudad;
import com.dh.PI.model.Producto;
import com.dh.PI.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoXId(@PathVariable Long id){
        Optional<Producto> productoBuscado = productoService.buscarProductoXId(id);
        if(productoBuscado.isPresent()){
            return ResponseEntity.ok(productoBuscado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Producto> buscarProductoXTitulo(@PathVariable String titulo){
        Optional<Producto> productoBuscado = productoService.buscarProductoXTitulo(titulo);
        if(productoBuscado.isPresent()){
            return ResponseEntity.ok(productoBuscado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{ciudad}")
    public ResponseEntity<List<Producto>> listarProductosXCiudad(@PathVariable Ciudad ciudad){
        return ResponseEntity.ok(productoService.listarProductosXCiudad(ciudad));
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) throws BadRequestException {
        //chequea que tenga todos los datos completos
        if(Producto.isProductoCompleto(producto)){
            return ResponseEntity.ok(productoService.agregarProducto(producto));
        }else{
            throw new BadRequestException("Error. Los datos del producto deben que estar 100% completos");
        }
    }

    @PutMapping
    public ResponseEntity<Producto> editarProducto(@RequestBody Producto producto){
        Optional<Producto> productoAEditar = productoService.buscarProductoXId(producto.getId());
        if(productoAEditar.isPresent()){
            Producto productoEditado = productoService.editarProducto(producto);
            return ResponseEntity.ok(productoEditado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Producto> productoABorrar = productoService.buscarProductoXId(id);
        if(productoABorrar.isPresent()){
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Se ha eliminado el producto con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se encontró el producto para realizar su eliminación");
        }
    }

}
