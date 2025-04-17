package pe.edu.utp.isi.dwi.s4.apirestful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private String codigo;
    private String nombre;
    private double precioUnitario;
    private int stock;
    private boolean estaActivo;

    public Producto(String codigo) {
        this.codigo = codigo;
    }
}
