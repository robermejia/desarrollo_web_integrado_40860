package pe.edu.utp.isi.dwi.sb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    private long id;
    private String descripcion;
    private boolean completada;

    public Tarea(long id) {
        this.id = id;
    }
}