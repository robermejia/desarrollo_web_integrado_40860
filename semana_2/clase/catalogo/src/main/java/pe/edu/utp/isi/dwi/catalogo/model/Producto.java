package pe.edu.utp.isi.dwi.catalogo.model;

import java.util.List;
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
    private List<byte[]> imagenes;
   
    public Producto(String codigo) {
        this.codigo = codigo;
    }
}