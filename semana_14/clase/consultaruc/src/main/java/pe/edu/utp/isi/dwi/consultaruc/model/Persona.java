package pe.edu.utp.isi.dwi.consultaruc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_persona_id")
    private Long id;
    @Column(name = "tb_persona_tipdocide")
    private String tipoDocumentoIdentidad;
    @Column(name = "tb_persona_numdocide")
    private String numeroDocumentoIdentidad;
    @Column(name = "tb_persona_apenomdenrazsoc")
    private String apellidosNombresDenominacionRazonSocial;

    public Persona(Long id) {
        this.id = id;
    }    
}
