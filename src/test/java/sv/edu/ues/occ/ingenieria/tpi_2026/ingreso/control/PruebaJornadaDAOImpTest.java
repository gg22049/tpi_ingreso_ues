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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaJornadaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaJornadaDAOImpTest.toEntityTest");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PruebaJornada result = cut.toEntity(new PruebaJornadaDTO(1L, 2L, Date.from(Instant.now()), "obs"));
        PruebaJornadaPK key = result.getPruebaJornadaPK();
        assertNotNull(key);
        assertEquals(1L, key.getIdPrueba());
        assertEquals(2L, key.getIdJornada());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaJornadaDAOImpTest.toDtoTest");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaJornadaDTO result = cut.toDto(new PruebaJornada(new PruebaJornadaPK(1L, 2L), Date.from(Instant.now()), "obs"));
        assertEquals(1L, result.idPrueba());
        assertEquals(2L, result.idJornada());
    }

}
