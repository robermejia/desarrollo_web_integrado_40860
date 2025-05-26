package com.utp.jwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String nombre;

  @Column(unique = true, nullable = false, length = 255)
  private String correo;

  @Column(nullable = false, length = 60)
  private String clave;

  @Column(nullable = false, length = 20)
  private String tipo;

}
