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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteIdentificacionDTO;
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
    public void toEntityTest() {
        System.out.println("AspiranteIdentificacionDAOImpTest.toEntityTest");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        cut.em = emMock;
        AspiranteIdentificacion result = cut.toEntity(new AspiranteIdentificacionDTO(1L, 1, "valor", "url", "obs"));
        assertNotNull(result);
        assertEquals(1L, result.getAspiranteIdentificacionPK().getIdAspirante());
        assertEquals(1, result.getAspiranteIdentificacionPK().getIdTipoIdentificacion());
    }

    @Test
    public void toDtoTest() {
        System.out.println("AspiranteIdentificacionDAOImpTest.toDtoTest");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        AspiranteIdentificacionDTO result = cut.toDto(new AspiranteIdentificacion(null, "val", "url", "obs"));
        assertEquals(0, result.idAspirante());
        assertEquals(0L, result.idTipoIdentificacion());
        result = cut.toDto(new AspiranteIdentificacion(new AspiranteIdentificacionPK(1L, 1), "val", "url", "obs"));
        assertNotNull(result);
        assertEquals(1L, result.idAspirante());
        assertEquals(1, result.idTipoIdentificacion());
    }

}
