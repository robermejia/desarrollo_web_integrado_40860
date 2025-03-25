package pe.edu.utp.isi.dwi.sb.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.isi.dwi.sb.models.Tarea;
import pe.edu.utp.isi.dwi.sb.services.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    private final TareaService tareaService;
    
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    } 
    
    @GetMapping
    public List<Tarea> consultarTareas() {
        return tareaService.consultarTareas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> consultarTareaPorId(@PathVariable long id) {
        Optional<Tarea> tarea = tareaService.consultarTareaPorId(id);
        return tarea.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PostMapping
    public ResponseEntity<Tarea> registrarTarea(@RequestBody Tarea tarea) {
        Tarea tareaRegistrada = tareaService.registrarTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaRegistrada);
    }
}