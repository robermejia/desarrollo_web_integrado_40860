package pe.edu.utp.isi.dwi.consultaruc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private String tipoDocumentoIdentidad;
    private String numeroDocumentoIdentidad;
    private String apellidosNombresDenominacionRazonSocial;    
}
