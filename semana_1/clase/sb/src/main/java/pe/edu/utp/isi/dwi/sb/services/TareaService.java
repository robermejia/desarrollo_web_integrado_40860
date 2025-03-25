package pe.edu.utp.isi.dwi.sb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pe.edu.utp.isi.dwi.sb.models.Tarea;

@Service
public class TareaService {
    private List<Tarea> tareas = new ArrayList<>();
    private long proximoId = -1;
    
    public List<Tarea> consultarTareas() {
        return tareas;
    }
    
    public Optional<Tarea> consultarTareaPorId(long id) {
        return tareas.stream().filter((tarea)->tarea.getId() == id).findFirst();
    }
    
    public Tarea registrarTarea(Tarea tarea) {
        tarea.setId(proximoId++);
        tareas.add(tarea);
        return tarea;        
    }
}