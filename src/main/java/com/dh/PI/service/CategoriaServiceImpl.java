package com.dh.PI.service;

import com.dh.PI.exceptions.BadRequestException;
import com.dh.PI.model.Caracteristica;
import com.dh.PI.model.Categoria;
import com.dh.PI.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarCategoriaXId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Optional<Categoria> buscarCategoriaXTitulo(String titulo) {
        return categoriaRepository.findByTitulo(titulo);
    }

    @Override
    public  Categoria agregarCategoria(Categoria categoria) throws BadRequestException {
        Optional<Categoria> categoriaXTitulo = categoriaRepository.findByTitulo(categoria.getTitulo());
        if (categoriaXTitulo.isPresent()) {
            throw new BadRequestException("Ya existe una categoria con ese nombre");
        } else {
            return categoriaRepository.save(categoria);
        }
    }

    @Override
    public Categoria editarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
