package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/**
 *
 * @author usermein
 */
public record AreaConocimientoDTO(
        Integer idAreaConocimiento,
        String nombre,
        String descripcion,
        Boolean activo,
        Integer idAreaConocimientoPadre) {

    public AreaConocimientoDTO(AreaConocimiento areaConocimiento) {
        this(areaConocimiento.getIdAreaConocimiento(),
                areaConocimiento.getNombre(),
                areaConocimiento.getDescripcion(),
                areaConocimiento.getActivo(),
                areaConocimiento.getIdAreaConocimientoPadre() != null
                ? areaConocimiento.getIdAreaConocimientoPadre().getIdAreaConocimiento() : null);

    }

}
