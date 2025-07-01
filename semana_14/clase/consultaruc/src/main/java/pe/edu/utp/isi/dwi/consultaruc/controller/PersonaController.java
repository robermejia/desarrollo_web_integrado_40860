package pe.edu.utp.isi.dwi.consultaruc.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.isi.dwi.consultaruc.dto.PersonaDTO;
import pe.edu.utp.isi.dwi.consultaruc.dto.PersonaMapper;
import pe.edu.utp.isi.dwi.consultaruc.model.Persona;
import pe.edu.utp.isi.dwi.consultaruc.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<PersonaDTO> consultar
    (
        @RequestParam String tipo,
        @RequestParam String numero
    ) {
        Optional<Persona> persona = personaService.consultar(tipo, numero);
        if(persona.isPresent()) {
            return ResponseEntity.ok().body(PersonaMapper.toPersonaDTO(persona.get()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }
}
