package pe.edu.utp.isi.dwi.catalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pe.edu.utp.isi.dwi.catalogo.model.Producto;

@Service
public class ProductoService {
    private List<Producto> productos = new ArrayList<>();
    
    public List<Producto> consultarPorNombre(String nombre) {
        return productos.stream().filter((producto)->producto.getNombre().contains(nombre)).toList();
    }
    
    public Optional<Producto> consultarPorCodigo(String codigo) {
        return productos.stream().filter((producto)->producto.getCodigo().equals(codigo)).findFirst();
    }
    
    public Producto registrar(Producto producto) {
        productos.add(producto);
        return producto;
    }
    
    public Producto modificar(Producto producto) {
        Producto productoEncontrado = consultarPorCodigo(producto.getCodigo()).orElse(null);
        
        if(productoEncontrado != null) {
            productoEncontrado.setNombre(producto.getNombre());
            productoEncontrado.setPrecioUnitario(producto.getPrecioUnitario());
            productoEncontrado.setStock(producto.getStock());
            productoEncontrado.setImagenes(producto.getImagenes());
        }
        
        return productoEncontrado;
    }
    
    public void eliminar(String codigo) {
        Producto productoEncontrado = consultarPorCodigo(codigo).orElse(null);
        
        if(productoEncontrado != null) {
            productos.remove(productoEncontrado);
        }
    }
}