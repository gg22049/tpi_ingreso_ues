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
    void constructorAndGetEntityManagerTest() {
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emMock;
        EntityManager result = cut.getEntityManager();
        assertNotNull(result);
        assertEquals(emMock, result);

    }
}
