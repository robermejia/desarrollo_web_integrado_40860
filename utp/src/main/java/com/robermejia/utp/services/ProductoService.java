package com.robermejia.utp.services;

import com.robermejia.utp.entities.Producto;
import com.robermejia.utp.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        try {
            return productoRepository.save(producto);
        } catch (DataAccessException e) {
            System.err.println("Error al guardar el producto: " + e.getMessage());
            return null; // o lanzar una excepción
        }
    }

    public Producto editarProducto(Producto producto) {
        try {
            return productoRepository.save(producto);
        } catch (DataAccessException e) {
            System.err.println("Error al editar el producto: " + e.getMessage());
            return null; // o lanzar una excepción
        }
    }

    public Optional<Producto> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto insUpd(Producto producto) {
        try {
            return productoRepository.save(producto);
        } catch (DataAccessException e) {
            System.err.println("Error al insertar/actualizar producto: " + e.getMessage());
            return null; // o lanzar una excepción
        }
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}