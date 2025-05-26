package com.utp.jwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 255, nullable = false)
  private String nombre;

  @Column(nullable = true)
  private String imagen;

  @Column(nullable = false)
  private Double precio;

  @Column(nullable = false)
  private Integer stock;

  @ManyToOne
  @JoinColumn(name = "categoria_id", referencedColumnName = "id")
  private Categoria categoria;
}
