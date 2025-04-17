package pe.edu.utp.isi.dwi.s4.apirestful.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.isi.dwi.s4.apirestful.dto.ProductoDTO;
import pe.edu.utp.isi.dwi.s4.apirestful.dto.ProductoMapper;
import pe.edu.utp.isi.dwi.s4.apirestful.exception.ProductoNoEncontradoException;
import pe.edu.utp.isi.dwi.s4.apirestful.model.Producto;
import pe.edu.utp.isi.dwi.s4.apirestful.service.ProductoService;

@Tag
(
    name = "Productos",
    description = "Operaciones relacionadas con la gestión de productos"
)
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @Operation(summary = "Listar productos", description = "Obtiene todos los productos registrados")
    @ApiResponses
    ({
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    @GetMapping("/{pagina}/{tamanoPagina}/{ordenadoPor}/{direccionOrdenamiento}")
    public List<Producto> findAll
    (
        @Parameter
        (
            description = "Número de página de productos a retornar",
            required = true,
            example = "1"
        )
        @PathVariable int pagina, 
            
        @Parameter
        (
            description = "Cantidad de productos que se retornan por página",
            required = true,
            example = "5"
        )    
        @PathVariable int tamanoPagina,
        
        @Parameter
        (
            description = "Criterio de ordenamiento de los productos a retornar",
            required = true,
            example = "nombre"
        )
        @PathVariable String ordenadoPor, 
        
        @Parameter
        (
            description = "Direccción de ordenamiento de los productos a retornar",
            required = true,
            example = "asc"
        )
        @PathVariable String direccionOrdenamiento
    ) {
        return productoService.findAll(pagina,tamanoPagina,ordenadoPor,direccionOrdenamiento);
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoDTO> findByCodigo(@PathVariable String codigo) {
        Producto producto = productoService.findByCodigo(codigo);
        
        if(producto == null) {
            throw new ProductoNoEncontradoException(codigo);
        }
        
        return ResponseEntity.ok(ProductoMapper.toDTO(producto));
    }
    
    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> update(@PathVariable String codigo, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.update(codigo, producto);
        return ResponseEntity.ok(productoActualizado);
    }
    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Producto> update(@PathVariable String codigo) {
        productoService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
