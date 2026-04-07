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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class JornadaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("JornadaDAOImpTest.toEntityTest");
        JornadaDAOImp cut = new JornadaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        Jornada result = cut.toEntity(new JornadaDTO(1L, "name", Date.from(Instant.now()), Date.from(Instant.now()), "obs"));
        assertNotNull(result);
        assertEquals(1L, result.getIdJornada());
    }

    @Test
    public void toDtoTest() {
        System.out.println("JornadaDAOImpTest.toDtoTest");
        JornadaDAOImp cut = new JornadaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        JornadaDTO result = cut.toDto(new Jornada(1L, "name", Date.from(Instant.now()), Date.from(Instant.now()), "obs"));
        assertNotNull(result);
        assertEquals(1L, result.idJornada());
    }

}
