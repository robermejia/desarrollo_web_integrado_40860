package pe.edu.utp.isi.dwi.catalogo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.isi.dwi.catalogo.model.Producto;
import pe.edu.utp.isi.dwi.catalogo.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/nombre")
    public List<Producto> consultarPorNombre(@RequestParam String nombre) {
        return productoService.consultarPorNombre(nombre);
    }
    
    @GetMapping("/codigo")
    public Producto consultarPorCodigo(@RequestParam String codigo) {
        return productoService.consultarPorCodigo(codigo).orElse(null);
    }
    
    @PostMapping
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
        Producto productoRegistrado = productoService.registrar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRegistrado);
    }
    
    @PutMapping
    public ResponseEntity<Producto> modificar(@RequestBody Producto producto) {
        Producto productoModificado = productoService.modificar(producto);
        return ResponseEntity.ok(productoModificado);
    }
    
    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestParam String codigo) {
        productoService.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }
}