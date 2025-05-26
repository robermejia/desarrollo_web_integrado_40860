package com.utp.jwt.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.jwt.model.Categoria;
import com.utp.jwt.repository.CategoriaRepository;
import com.utp.jwt.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaRepository categoriaRepository;

  @Override
  public List<Categoria> listAll() {
    return this.categoriaRepository.findAll();
  }

  @Override
  public Categoria guardar(Categoria categoria) {
    return this.categoriaRepository.save(categoria);
  }

  @Override
  public Categoria editar(Categoria categoria) {
    return this.categoriaRepository.save(categoria);
  }

  @Override
  public void eliminar(Categoria categoria) {
    this.categoriaRepository.delete(categoria);
  }

}
