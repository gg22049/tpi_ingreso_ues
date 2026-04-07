/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamenPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaJornadaAulaAspiranteOpcionExamenDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImpTest.toEntityTest");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PruebaJornadaAulaAspiranteOpcionExamen result = cut.toEntity(new PruebaJornadaAulaAspiranteOpcionExamenDTO(1L, 2L, "3", 4L, BigDecimal.ONE, Date.from(Instant.now()), "url", "obs"));
        PruebaJornadaAulaAspiranteOpcionExamenPK key = result.getPruebaJornadaAulaAspiranteOpcionExamenPK();
        assertNotNull(key);
        assertEquals(1L, key.getIdPrueba());
        assertEquals(2L, key.getIdJornada());
        assertEquals("3", key.getIdAula());
        assertEquals(4L, key.getIdAspiranteOpcion());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImpTest.toDtoTest");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaJornadaAulaAspiranteOpcionExamenDTO result = cut.toDto(new PruebaJornadaAulaAspiranteOpcionExamen(new PruebaJornadaAulaAspiranteOpcionExamenPK(1L, 2L, "3", 4L), BigDecimal.ONE, Date.from(Instant.now()), "url", "obs"));
        assertEquals(1L, result.idPrueba());
        assertEquals(2L, result.idJornada());
        assertEquals("3", result.idAula());
        assertEquals(4L, result.idAspiranteOpcion());
    }

}
