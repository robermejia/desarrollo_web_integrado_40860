package com.robermejia.utp.entities;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "La imagen es obligatoria")
    @URL(message = "Debe ser una URL válida")
    private String imagen;

    @Positive(message = "El precio debe ser positivo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0.00")
    private double precio;

    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private int stock;

    @Min(value = 0, message = "La categoría es obligatoria")
    private Integer categoria_id;

    public Producto() {}

    public Producto(String nombre, String imagen, double precio, int stock, Integer categoria_id) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.stock = stock;
        this.categoria_id = categoria_id;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategoria_id(Integer categoria_id) { this.categoria_id = categoria_id; }
    public Integer getCategoria_id() { return categoria_id; }
}