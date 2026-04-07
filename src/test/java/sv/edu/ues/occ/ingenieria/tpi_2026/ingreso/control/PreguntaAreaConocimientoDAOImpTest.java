/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5Suite.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PreguntaAreaConocimientoDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PreguntaAreaConocimientoDAOImpTest.toEntityTest");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PreguntaAreaConocimiento result = result = cut.toEntity(new PreguntaAreaConocimientoDTO(1L, 1, "obs"));
        assertNotNull(result.getPreguntaAreaConocimientoPK());
        assertEquals(1L, result.getPreguntaAreaConocimientoPK().getIdPregunta());
        assertEquals(1, result.getPreguntaAreaConocimientoPK().getIdAreaConocimiento());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PreguntaAreaConocimientoDAOImpTest.toDtoTest");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PreguntaAreaConocimientoDTO result = cut.toDto(new PreguntaAreaConocimiento(null, "obs"));
        assertNotNull(result);
        assertEquals(0L, result.idPregunta());
        assertEquals(0, result.idAreaConocimiento());
        result = cut.toDto(new PreguntaAreaConocimiento(new PreguntaAreaConocimientoPK(1L, 1), "obs"));
        assertNotNull(result);
        assertEquals(1L, result.idPregunta());
        assertEquals(1, result.idAreaConocimiento());
    }

}
