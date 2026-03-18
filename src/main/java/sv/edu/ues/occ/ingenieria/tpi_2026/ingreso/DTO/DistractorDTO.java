package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;

/**
 *
 * @author usermein
 */
public record DistractorDTO(long idDistractor,
        String valor,
        Boolean activo,
        String imagenUrl) {

    public DistractorDTO(Distractor distractor) {
        this(distractor.getIdDistractor(),
                distractor.getValor(),
                distractor.getActivo(),
                distractor.getImagenUrl());
    }
}
