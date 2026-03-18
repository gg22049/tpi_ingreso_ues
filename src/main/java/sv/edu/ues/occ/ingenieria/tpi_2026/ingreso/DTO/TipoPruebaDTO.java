package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
public record TipoPruebaDTO(
        Integer idTipoPrueba,
        String valor,
        Boolean activo,
        String observaciones
        ) {

    public TipoPruebaDTO(TipoPrueba tipoPrueba) {
        this(
                tipoPrueba.getIdTipoPrueba(),
                tipoPrueba.getValor(),
                tipoPrueba.getActivo(),
                tipoPrueba.getObservaciones()
        );
    }

}
