/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5Suite.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class AspiranteDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("AspiranteDAOImpTest.toEntityTest");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        Aspirante result = cut.toEntity(new AspiranteDTO(1L, "name", "last name", Date.from(Instant.now()), "email", null, "obs"));
        assertNotNull(result);
        assertEquals(1L, result.getIdAspirante());
        assertNotNull(result.getFechaNacimiento());
    }

    @Test
    public void toDtoTest() {
        System.out.println("AspiranteDAOImpTest.toDtoTest");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        AspiranteDTO result = cut.toDto(new Aspirante(1L, "name", "last name", Date.from(Instant.now()), "email", null, "obs"));
        assertNotNull(result);
        assertEquals(1L, result.idAspirante());
        assertNotNull(result.fechaNacimiento());
    }

}
