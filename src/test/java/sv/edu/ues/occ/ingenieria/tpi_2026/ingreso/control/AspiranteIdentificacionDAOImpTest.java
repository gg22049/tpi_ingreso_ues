/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5Suite.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacionPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class AspiranteIdentificacionDAOImpTest {

    @Mock
    EntityManager emMock;
    
@Test
void constructorAndGetEntityManagerTest() {

    AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
    cut.em = emMock;
    EntityManager result = cut.getEntityManager();
    assertNotNull(result);
    assertEquals(emMock, result);
    AspiranteIdentificacion aspiranteIdentificacion = new AspiranteIdentificacion();
    aspiranteIdentificacion.setAspiranteIdentificacionPK(new AspiranteIdentificacionPK(1l, 0));

}
   
}
