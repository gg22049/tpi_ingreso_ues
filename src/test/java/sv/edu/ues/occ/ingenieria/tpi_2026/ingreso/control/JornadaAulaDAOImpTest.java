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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaAulaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class JornadaAulaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("JornadaAulaDAOImpTest.toEntityTest");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        cut.em = emMock;
        JornadaAula result = cut.toEntity(new JornadaAulaDTO(1L, "1", "obs"));
        assertNotNull(result);
        assertNotNull(result.getJornadaAulaPK());
        assertEquals(1L, result.getJornadaAulaPK().getIdJornada());
        assertEquals("1", result.getJornadaAulaPK().getIdAula());
    }

    @Test
    public void toDtoTest() {
        System.out.println("JornadaAulaDAOImpTest.toDtoTest");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        JornadaAulaDTO result = cut.toDto(new JornadaAula(null, "obs"));
        assertNotNull(result);
        assertEquals("", result.idAula());
        assertEquals(0L, result.idJornada());
        result = cut.toDto(new JornadaAula(new JornadaAulaPK(1L, "1"), "obs"));
        assertNotNull(result);
        assertEquals("1", result.idAula());
        assertEquals(1L, result.idJornada());
    }

}
