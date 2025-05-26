package com.utp.jwt.services;

import java.util.List;

import com.utp.jwt.model.Categoria;

public interface CategoriaService {

  public List<Categoria> listAll();

  public Categoria guardar(Categoria categoria);

  public Categoria editar(Categoria categoria);

  public void eliminar(Categoria categoria);

}
