package com.robermejia.utp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.robermejia.utp.repositories.CategoriaRepository;
import com.robermejia.utp.repositories.ProductoRepository;


import org.springframework.stereotype.Service;
import com.robermejia.utp.entities.Categoria;
import java.lang.Exception;
import java.util.Optional;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias() {
        return categoriaRepository.findAllByOrderByIdAsc();
    }

    public Categoria guardarCategoria(Categoria categoria) {
        try {
            return categoriaRepository.save(categoria);
        } catch (DataAccessException e) {
            System.err.println("Error al guardar la categoría: " + e.getMessage());
            return null;
        }
    }

    public Optional<Categoria> getCategoriaById(Integer id) {
        return categoriaRepository.findById(id);
    }

    public void deleteCategoria(Integer id) throws Exception {
        long count = productoRepository.countCategoria(id);
        if(count > 0){
            throw new Exception("La categoría tiene productos asignados, no se puede eliminar");
        }
        categoriaRepository.deleteById(id);
    }
}
