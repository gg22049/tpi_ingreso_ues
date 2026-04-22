/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityTransaction;
import java.util.Calendar;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AspiranteOpcionDAOImpIT extends ITAbstract {

    AspiranteOpcion newAspiranteOpcion;
    Aspirante parentAspirante;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        parentAspirante = new Aspirante(null, "TEST", "RELACION", cal.getTime(), "relacion@test.com");

        newAspiranteOpcion = new AspiranteOpcion(null);

        newAspiranteOpcion.setIdOpcion("I300515");
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("AspiranteOpcionDAOImp.testCreate");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();

            cut.em.persist(parentAspirante);

            cut.em.flush();
            cut.em.clear();
            newAspiranteOpcion.setIdAspirante(parentAspirante);
            cut.create(newAspiranteOpcion);
            cut.em.flush();
            AspiranteOpcion creado = cut.em.find(AspiranteOpcion.class, newAspiranteOpcion.getIdAspiranteOpcion());
            assertNotNull(creado);
            assertEquals("I300515", creado.getIdOpcion());
            assertEquals(parentAspirante, creado.getIdAspirante());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("AspiranteOpcionDAOImp.testFindById");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(parentAspirante);
            cut.em.flush();

            newAspiranteOpcion.setIdAspirante(parentAspirante);
            cut.em.persist(newAspiranteOpcion);
            cut.em.flush();
            cut.em.clear();

            AspiranteOpcion found = cut.em.find(AspiranteOpcion.class, newAspiranteOpcion.getIdAspiranteOpcion());

            assertNotNull(found);
            assertEquals(newAspiranteOpcion.getIdOpcion(), found.getIdOpcion());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("AspiranteOpcionImp.testFindAll");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);

            cut.em.flush();
            newAspiranteOpcion.setIdAspirante(parentAspirante);
            cut.em.persist(newAspiranteOpcion);
            cut.em.flush();
            cut.em.clear();
            List<AspiranteOpcion> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() > 0);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("AspiranteOpcionDAOImp.testFindRange");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);
            cut.em.flush();

            newAspiranteOpcion.setIdAspirante(parentAspirante);
            cut.em.persist(newAspiranteOpcion);
            cut.em.flush();
            cut.em.clear();
            List<AspiranteOpcion> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() > 0);

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("AspiranteOpcionDAOImp.testUpdate");
        String expected = "I515300";
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);

            cut.em.flush();

            cut.em.persist(newAspiranteOpcion);
            cut.em.flush();

            newAspiranteOpcion.setIdOpcion(expected);
            newAspiranteOpcion = cut.update(newAspiranteOpcion);
            cut.em.flush();

            assertNotNull(newAspiranteOpcion);
            assertEquals(expected, newAspiranteOpcion.getIdOpcion());

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("AspiranteOpcionDAOImp.testDelete");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(parentAspirante);

            cut.em.flush();

            newAspiranteOpcion.setIdAspirante(parentAspirante);
            cut.em.persist(newAspiranteOpcion);
            cut.em.flush();
            cut.em.clear();
            assertNotNull(parentAspirante.getIdAspirante());
            cut.delete(newAspiranteOpcion);

            AspiranteOpcion deleted = cut.findById(newAspiranteOpcion.getIdAspiranteOpcion());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("AspiranteOpcionDAOImp.testCount");
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);

            cut.em.flush();
            cut.em.clear();

            newAspiranteOpcion.setIdAspirante(parentAspirante);
            cut.em.persist(newAspiranteOpcion);
            cut.em.flush();
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos > 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("AspiranteOpcionDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        AspiranteOpcionDAOImp aspiranteOpcionDI = new AspiranteOpcionDAOImp();
        AspiranteOpcion aspiranteOpcion1 = new AspiranteOpcion(1l, "I300515", null);
        Aspirante aspi = new Aspirante(1l);
        AspiranteOpcionDTO aspiranteOpcionDTO;
        AspiranteOpcion aspiranteOpcion2 = null;
        AspiranteOpcion aspiranteOpcion3 = new AspiranteOpcion();
        aspiranteOpcion3.setIdAspirante(aspi);
        assertThrows(IllegalStateException.class, () -> {
            aspiranteOpcionDI.toDto(aspiranteOpcion2);

        });

        aspiranteOpcionDTO = aspiranteOpcionDI.toDto(aspiranteOpcion1);
        assertNotNull(aspiranteOpcionDTO);
        AspiranteOpcionDTO aspiDTO = aspiranteOpcionDI.toDto(aspiranteOpcion3);
        assertNotNull(aspiDTO);
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("AspiranteOpcionDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        AspiranteOpcionDAOImp cut = new AspiranteOpcionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            AspiranteOpcionDTO aspiranteOpcionDTO1 = new AspiranteOpcionDTO(null, "I300515", cal.getTime(), 1l);
            AspiranteOpcionDTO aspiranteOpcionDTO2 = new AspiranteOpcionDTO(null, "I300515", cal.getTime(), null);
            AspiranteOpcion aspiranteOpcion;
            AspiranteOpcion aspiranteOpcion3;
            AspiranteOpcionDTO aspiranteOpcion2 = null;
            assertThrows(IllegalStateException.class, () -> {
                cut.toEntity(aspiranteOpcion2);

            });

            aspiranteOpcion = cut.toEntity(aspiranteOpcionDTO1);
            assertNotNull(aspiranteOpcion);
            aspiranteOpcion3 = cut.toEntity(aspiranteOpcionDTO2);
            assertNotNull(aspiranteOpcion3);
            assertNull(aspiranteOpcion3.getIdAspirante());
        } finally {

            cut.em.close();
        }

    }
}
