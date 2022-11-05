package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Ciudad;
import com.dh.PI.model.Producto;
import com.dh.PI.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarProductoXId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> buscarProductoXTitulo(String titulo) {
        return productoRepository.findByTitulo(titulo);
    }

    @Override
    public List<Producto> listarProductosXCiudad(Ciudad ciudad) {
        return productoRepository.listarProductosXCiudad(ciudad);
    }

    @Override
    public Producto agregarProducto(Producto producto) throws BadRequestException {
        Optional<Producto> productoXTitulo = productoRepository.findByTitulo(producto.getTitulo());
        if (productoXTitulo.isPresent()) {
            throw new BadRequestException("Ya existe un producto con ese nombre");
        } else {
            return productoRepository.save(producto);
        }
    }

    @Override
    public Producto editarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

}
