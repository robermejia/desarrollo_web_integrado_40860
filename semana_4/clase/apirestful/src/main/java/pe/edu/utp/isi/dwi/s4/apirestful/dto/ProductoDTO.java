package pe.edu.utp.isi.dwi.s4.apirestful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private String codigo;
    private String nombre;
    private double precioUnitario;
    private int stock;

    public ProductoDTO(String codigo) {
        this.codigo = codigo;
    }
}
