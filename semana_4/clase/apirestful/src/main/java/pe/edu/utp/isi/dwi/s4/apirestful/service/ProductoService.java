package pe.edu.utp.isi.dwi.s4.apirestful.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pe.edu.utp.isi.dwi.s4.apirestful.model.Producto;

@Service
public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    public ProductoService() {
        productos.add(new Producto("01", "INKA KOLA", 3, 250, true)); //2
        productos.add(new Producto("02", "TRIPLE KOLA", 2, 40, true)); //5
        productos.add(new Producto("03", "COCA COLA", 3.5, 180, true)); //1
        productos.add(new Producto("04", "PEPSI COLA", 3, 90, true)); //3
        productos.add(new Producto("05", "PERÚ COLA", 1.5, 45, true)); //4
    }

    public List<Producto> findAll(int pagina, int tamanoPagina, String ordenadoPor, String direccionOrdenamiento) {
        Comparator<Producto> comparator;
        
        switch(ordenadoPor) {
            case "codigo":
                comparator = Comparator.comparing(Producto::getCodigo);
                break;
            case "nombre":
                comparator = Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER);
                break;
            case "precioUnitario":
                comparator = Comparator.comparingDouble(Producto::getPrecioUnitario);
                break;
            case "stock":
                comparator = Comparator.comparingInt(Producto::getStock);
                break;
            default: 
                comparator = Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER);
                break;
        }
        
        if("desc".equals(direccionOrdenamiento)) {
            comparator = comparator.reversed();
        }
        
        return productos.stream()
        .sorted(comparator)
        .skip((pagina - 1) * tamanoPagina)
        .limit(tamanoPagina)
        .collect(Collectors.toList());
    }

    public Producto findByCodigo(String codigo) {
        return productos.stream().filter((p) -> p.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    public Producto save(Producto producto) {
        productos.add(producto);
        return producto;
    }

    public Producto update(String codigo, Producto producto) {
        productos.forEach(p
                -> {
            if (p.getCodigo().equals(codigo)) {
                p.setNombre(producto.getNombre());
                p.setPrecioUnitario(producto.getPrecioUnitario());
                p.setStock(producto.getStock());
                p.setEstaActivo(producto.isEstaActivo());
            }
        }
        );

        return producto;
    }

    public boolean delete(String codigo) {
        return productos.removeIf(p -> p.getCodigo().equals(codigo));
    }
}
