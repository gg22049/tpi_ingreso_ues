package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;

/**
 *
 * @author usermein
 */
public record PruebaClaveDTO(
        Long idPruevaClave,
        String nombre,
        Long idPrueba) {

    public PruebaClaveDTO(PruebaClave pruebaClave) {
        this(
                pruebaClave.getIdPruebaClave(),
                pruebaClave.getNombre(),
                pruebaClave.getIdPrueba() != null
                ? pruebaClave.getIdPrueba().getIdPrueba() : null
        );

    }

}
