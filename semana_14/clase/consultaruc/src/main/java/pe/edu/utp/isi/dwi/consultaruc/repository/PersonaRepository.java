package pe.edu.utp.isi.dwi.consultaruc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.utp.isi.dwi.consultaruc.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
    @Query("select p from Persona p where p.tipoDocumentoIdentidad = :tipo and p.numeroDocumentoIdentidad = :numero")
    public Optional<Persona> findByTipoNumeroDocumentoIdentidad(@Param("tipo") String tipo, @Param("numero") String numero);
}
