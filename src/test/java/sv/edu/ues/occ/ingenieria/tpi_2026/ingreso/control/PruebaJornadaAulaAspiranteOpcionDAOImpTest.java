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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaJornadaAulaAspiranteOpcionDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionDAOImpTest.toEntityTest");
        PruebaJornadaAulaAspiranteOpcionDI cut = new PruebaJornadaAulaAspiranteOpcionDI();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PruebaJornadaAulaAspiranteOpcion result = cut.toEntity(new PruebaJornadaAulaAspiranteOpcionDTO(1L, 2L, "3", 4L, Boolean.TRUE, Date.from(Instant.now())));
        PruebaJornadaAulaAspiranteOpcionPK key = result.getPruebaJornadaAulaAspiranteOpcionPK();
        assertNotNull(key);
        assertEquals(1L, key.getIdPrueba());
        assertEquals(2L, key.getIdJornada());
        assertEquals("3", key.getIdAula());
        assertEquals(4L, key.getIdAspiranteOpcion());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionDAOImpTest.toDtoTest");
        PruebaJornadaAulaAspiranteOpcionDI cut = new PruebaJornadaAulaAspiranteOpcionDI();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaJornadaAulaAspiranteOpcionDTO result = cut.toDto(new PruebaJornadaAulaAspiranteOpcion(new PruebaJornadaAulaAspiranteOpcionPK(1L, 2L, "3", 4L), Boolean.TRUE, Date.from(Instant.now())));
        assertEquals(1L, result.idPrueba());
        assertEquals(2L, result.idJornada());
        assertEquals("3", result.idAula());
        assertEquals(4L, result.idAspiranteOpcion());
    }

}
