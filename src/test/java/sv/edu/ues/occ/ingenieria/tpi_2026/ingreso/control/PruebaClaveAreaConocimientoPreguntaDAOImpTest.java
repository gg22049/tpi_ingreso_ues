/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaClaveAreaConocimientoPreguntaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImpTest.toEntityTest");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PruebaClaveAreaConocimientoPregunta result = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDTO(1L, 2, 3L, BigDecimal.ONE));
        PruebaClaveAreaConocimientoPreguntaPK key = result.getPruebaClaveAreaConocimientoPreguntaPK();
        assertEquals(1L, key.getIdPruebaClave());
        assertEquals(2, key.getIdAreaConocimiento());
        assertEquals(3L, key.getIdPregunta());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImpTest.toDtoTest");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaClaveAreaConocimientoPreguntaDTO result = cut.toDto(new PruebaClaveAreaConocimientoPregunta(new PruebaClaveAreaConocimientoPreguntaPK(1L, 2, 3L), BigDecimal.ONE));
        assertEquals(1L, result.idPruebaClave());
        assertEquals(2, result.idAreaConocimiento());
        assertEquals(3L, result.idPregunta());
    }

}
