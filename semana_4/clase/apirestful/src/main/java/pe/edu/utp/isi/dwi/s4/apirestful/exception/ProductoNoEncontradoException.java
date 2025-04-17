package pe.edu.utp.isi.dwi.s4.apirestful.exception;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String codigo) {
        super("Producto con código '" + codigo + "' no encontrado");
    }
}
