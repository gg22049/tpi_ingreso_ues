package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 *
 * @author usermein
 */
public record AspiranteDTO(
        Long idAspirante,
        @NotNull
        @Size(min = 1, max = 64)
        String nombres,
        @NotNull
        @Size(min = 1, max = 64)
        String apellidos,
        @NotNull
        Date fechaNacimiento,
        @NotNull
        @Size(min = 1, max = 124)
        String correo,
        Date fechaCreacion,
        String observaciones
        ) {

}
