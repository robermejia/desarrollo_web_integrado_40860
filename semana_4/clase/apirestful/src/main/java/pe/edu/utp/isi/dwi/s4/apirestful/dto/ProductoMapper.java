package pe.edu.utp.isi.dwi.s4.apirestful.dto;

import pe.edu.utp.isi.dwi.s4.apirestful.model.Producto;

public class ProductoMapper {
    public static ProductoDTO toDTO(Producto producto) {
        return new ProductoDTO
        (
            producto.getCodigo(),
            producto.getNombre(),
            producto.getPrecioUnitario(),
            producto.getStock()
        );
    }
}
