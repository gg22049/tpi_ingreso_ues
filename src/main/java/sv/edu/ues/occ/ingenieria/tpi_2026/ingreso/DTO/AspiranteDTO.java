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
    
    public AspiranteDTO{
        if (nombres==null || apellidos==null || fechaNacimiento==null || correo==null || fechaCreacion==null) {
          throw new IllegalArgumentException("Todos los campos deben de ser validos");
        }
    }
    
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
    
    public Aspirante toEntity(){
        Aspirante aspirante=new Aspirante();
        aspirante.setIdAspirante(idAspirante);
        aspirante.setNombres(nombres);
        aspirante.setApellidos(apellidos);
        aspirante.setFechaNacimiento(fechaNacimiento);
        aspirante.setCorreo(correo);
        aspirante.setFechaCreacion(fechaCreacion);
        aspirante.setObservaciones(observaciones);
        return aspirante;
    }

}
