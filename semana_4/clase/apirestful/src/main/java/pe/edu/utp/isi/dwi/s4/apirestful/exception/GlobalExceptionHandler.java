package pe.edu.utp.isi.dwi.s4.apirestful.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<Map<String,Object>> handleProductoNoEncontrado(ProductoNoEncontradoException ex) {
        Map<String,Object> errorBody = new HashMap<>();
        
        errorBody.put("timestamp",LocalDateTime.now());
        errorBody.put("status",HttpStatus.NOT_FOUND.value());
        errorBody.put("error","Producto no encontrado");
        errorBody.put("menssage",ex.getMessage());
        
        return new ResponseEntity<>(errorBody,HttpStatus.NOT_FOUND);
    }
}
