package com.utp.jwt.services;

import java.util.List;

import com.utp.jwt.http.dto.product.StoreProductDTO;
import com.utp.jwt.model.Producto;

public interface ProductoService {

  public List<Producto> listAll();

  public Producto guardar(StoreProductDTO productoData);

  public Producto editar(Producto producto);

  public void eliminar(Producto producto);

}
