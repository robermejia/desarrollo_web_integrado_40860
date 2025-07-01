package pe.edu.utp.isi.dwi.consultaruc.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.isi.dwi.consultaruc.model.Persona;
import pe.edu.utp.isi.dwi.consultaruc.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    
    public Optional<Persona> consultar(String tipo, String numero) {
        return personaRepository.findByTipoNumeroDocumentoIdentidad(tipo, numero);
    }
}