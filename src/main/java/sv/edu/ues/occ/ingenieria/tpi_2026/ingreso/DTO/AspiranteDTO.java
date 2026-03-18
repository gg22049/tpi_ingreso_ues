package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;

/**
 *
 * @author usermein
 */
public record AspiranteDTO(Long idAspirante, 
        String nombres,
        String apellidos,
        Date fechaNacimiento,
        String correo,
        Date fechaCreacion,
        String observaciones
        ) {
    
 // este con
    public AspiranteDTO(Aspirante aspirante ){
        this(
        aspirante.getIdAspirante(),
        aspirante.getNombres(),
        aspirante.getApellidos(),
        aspirante.getFechaNacimiento(),
        aspirante.getCorreo(),
        aspirante.getFechaCreacion(),
        aspirante.getObservaciones()
        );
    }
    

}
