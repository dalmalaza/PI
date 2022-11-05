package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Ciudad;
import com.dh.PI.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarProductos();
    Optional<Producto> buscarProductoXId(Long id);
    Optional<Producto> buscarProductoXTitulo(String titulo);
    List<Producto> listarProductosXCiudad (Ciudad ciudad);
    Producto agregarProducto(Producto producto) throws BadRequestException;
    Producto editarProducto(Producto producto);
    void eliminarProducto(Long id);

}
