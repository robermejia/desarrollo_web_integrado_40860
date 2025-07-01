package pe.edu.utp.isi.dwi.consultaruc.dto;

import pe.edu.utp.isi.dwi.consultaruc.model.Persona;

public class PersonaMapper {
    public static PersonaDTO toPersonaDTO(Persona persona) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setTipoDocumentoIdentidad(persona.getTipoDocumentoIdentidad());
        personaDTO.setNumeroDocumentoIdentidad(persona.getNumeroDocumentoIdentidad());
        personaDTO.setApellidosNombresDenominacionRazonSocial(persona.getApellidosNombresDenominacionRazonSocial());
        return personaDTO;
    }
}
