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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveDTO;
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
    public void toEntityTest() {
        System.out.println("PruebaClaveDAOImpTest.toEntityTest");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        cut.em = emMock;
        when(emMock.find(Prueba.class, 1L)).thenReturn(new Prueba(1L));
        PruebaClave result = cut.toEntity(new PruebaClaveDTO(1L, "name", null));
        assertNull(result.getIdPrueba());
        assertEquals(1L, result.getIdPruebaClave());
        result = cut.toEntity(new PruebaClaveDTO(1L, "name", 1L));
        assertNotNull(result.getIdPrueba());
        assertEquals(1L, result.getIdPruebaClave());
        assertEquals(1L, result.getIdPrueba().getIdPrueba());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaClaveDAOImpTest.toDtoTest");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaClaveDTO result = cut.toDto(new PruebaClave(1L, "name", null));
        assertNull(result.idPrueba());
        assertEquals(1L, result.idPruevaClave());
        result = cut.toDto(new PruebaClave(1L, "name", new Prueba(1L)));
        assertEquals(1L, result.idPruevaClave());
        assertEquals(1L, result.idPrueba());
    }

}
