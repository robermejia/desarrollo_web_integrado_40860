package com.utp.jwt.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.jwt.http.dto.product.StoreProductDTO;
import com.utp.jwt.model.Categoria;
import com.utp.jwt.model.Producto;
import com.utp.jwt.repository.ProductoRepository;
import com.utp.jwt.services.ProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;

  @Override
  public List<Producto> listAll() {
    return this.productoRepository.findAll();
  }

  @Override
  public Producto guardar(StoreProductDTO productoData) {

    Producto producto = new Producto();
    producto.setNombre(productoData.getNombre());
    producto.setImagen(productoData.getImagen());
    producto.setPrecio(productoData.getPrecio());
    producto.setStock(productoData.getStock());
    Categoria categoria = new Categoria();
    categoria.setId(productoData.getCategoriaId());
    producto.setCategoria(categoria);

    return this.productoRepository.save(producto);
  }

  @Override
  public Producto editar(Producto producto) {
    return this.productoRepository.save(producto);
  }

  @Override
  public void eliminar(Producto producto) {
    this.productoRepository.delete(producto);
  }

}
