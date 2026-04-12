/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5Suite.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void findByEmailTest() {
        System.out.println("AspiranteDAOImpTest.findByEmailTest");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.findByEmail(null);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.findByEmail("");
                });
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.findByEmail("test@test.com");
                });
        cut.em = emMock;
        TypedQuery tqMock = mock(TypedQuery.class);
        when(emMock.createNamedQuery("Aspirante.findByCorreo", Aspirante.class)).thenReturn(tqMock);
        when(tqMock.setParameter("correo", "test@test.com")).thenReturn(tqMock);
        when(tqMock.getSingleResult()).thenThrow(new NoResultException()).thenReturn(new Aspirante(1L));
        Aspirante result = cut.findByEmail("test@test.com");
        assertNull(result);
        result = cut.findByEmail("test@test.com");
        assertNotNull(result);
        assertEquals(1l, result.getIdAspirante());
    }

}
