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
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class AspiranteOpcionDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("AspiranteOpcionDAOImpTest.toEntityTest");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        cut.em = emMock;
        AspiranteOpcion result = cut.toEntity(new AspiranteOpcionDTO(1L, "1", Date.from(Instant.now()), null));
        assertNull(result.getIdAspirante());
        assertEquals(1L, result.getIdAspiranteOpcion());
        assertEquals("1", result.getIdOpcion());
        when(emMock.find(Aspirante.class, 2L)).thenReturn(new Aspirante(2L));
        result = cut.toEntity(new AspiranteOpcionDTO(1L, "1", Date.from(Instant.now()), 2L));
        assertEquals(2L, result.getIdAspirante().getIdAspirante());
        assertEquals(1L, result.getIdAspiranteOpcion());
        assertEquals("1", result.getIdOpcion());
    }

    @Test
    public void toDtoTest() {
        System.out.println("AspiranteOpcionDAOImpTest.toDtoTest");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        AspiranteOpcionDTO result = cut.toDto(new AspiranteOpcion(1L, "val", Date.from(Instant.now()), null));
        assertNotNull(result.fechaCreacion());
        assertEquals(1L, result.idAspiranteOpcion());
        assertNull(result.idAspirante());
        result = cut.toDto(new AspiranteOpcion(1L, "val", Date.from(Instant.now()), new Aspirante(2L)));
        assertNotNull(result.fechaCreacion());
        assertEquals(1L, result.idAspiranteOpcion());
        assertNotNull(result.idAspirante());
    }

}
