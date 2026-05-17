/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaClaveDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
void constructorAndGetEntityManagerTest() {
    PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
    cut.em = emMock;
    EntityManager result = cut.getEntityManager();
    assertNotNull(result);
    assertEquals(emMock, result);

}
}
