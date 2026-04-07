/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaClaveAreaConocimientoPreguntaDistractorDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImpTest.toEntityTest");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PruebaClaveAreaConocimientoPreguntaDistractor result = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDistractorDTO(1L, 1, 1L, 1L, Date.from(Instant.now()), "obs"));
        assertEquals(1L, result.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdPruebaClave());
        assertEquals(1, result.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdAreaConocimiento());
        assertEquals(1L, result.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdPregunta());
        assertEquals(1L, result.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdDistractor());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImpTest.toDtoTest");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaClaveAreaConocimientoPreguntaDistractorDTO result = cut.toDto(new PruebaClaveAreaConocimientoPreguntaDistractor(null, Date.from(Instant.now()), "obs"));
        assertEquals(0L, result.idPruebaClave());
        assertEquals(0, result.idAreaConocimiento());
        assertEquals(0L, result.idPregunta());
        assertEquals(0L, result.idDistractor());
        result = cut.toDto(new PruebaClaveAreaConocimientoPreguntaDistractor(new PruebaClaveAreaConocimientoPreguntaDistractorPK(1L, 1, 1L, 1L), Date.from(Instant.now()), "obs"));
    }

}
