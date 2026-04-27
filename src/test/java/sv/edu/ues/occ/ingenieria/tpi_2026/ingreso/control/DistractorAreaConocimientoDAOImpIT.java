/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DistractorAreaConocimientoDAOImpIT extends ITAbstract {

    DistractorAreaConocimiento distractorAreaConocimiento;
    Distractor distractor;
    AreaConocimiento areaConocimirnto;
    DistractorAreaConocimientoPK disAreaPK;

    private void persistirEscenarioCompleto(EntityManager em) {
        em.persist(distractor);
        em.persist(areaConocimirnto);
        em.flush();

        disAreaPK = new DistractorAreaConocimientoPK(
                distractor.getIdDistractor(), areaConocimirnto.getIdAreaConocimiento()
        );
        distractorAreaConocimiento.setDistractorAreaConocimientoPK(disAreaPK);
        em.persist(distractorAreaConocimiento);
        em.flush();
    }

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        distractor = new Distractor(null, "Respuesta Correcta", true, "IMAGEN");
        areaConocimirnto = new AreaConocimiento(null, "PROGRAMACION", "PREGUNTAS SOBRE JAVA", true, null);

        distractorAreaConocimiento = new DistractorAreaConocimiento(null, "TEST DISTRACTORAREA");

        distractorAreaConocimiento.setDistractor(distractor);
        distractorAreaConocimiento.setAreaConocimiento(areaConocimirnto);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("DistractorAreaConocimientoDAOImp.testCreate");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.em.persist(distractor);
            cut.em.persist(areaConocimirnto);
            cut.em.flush();
            disAreaPK = new DistractorAreaConocimientoPK(distractor.getIdDistractor(), areaConocimirnto.getIdAreaConocimiento());
            distractorAreaConocimiento.setDistractorAreaConocimientoPK(disAreaPK);
            cut.em.clear();
            cut.create(distractorAreaConocimiento);
            DistractorAreaConocimientoPK pk = distractorAreaConocimiento.getDistractorAreaConocimientoPK();
            assertNotNull(pk);
            DistractorAreaConocimiento creado = cut.em.find(DistractorAreaConocimiento.class, pk);
            assertNotNull(creado);
            assertEquals("TEST DISTRACTORAREA", creado.getObservaciones());
            assertEquals(distractorAreaConocimiento.getDistractorAreaConocimientoPK(), creado.getDistractorAreaConocimientoPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("DistractorAreaConocimientoDAOImp.testFindById");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            persistirEscenarioCompleto(cut.em);
            cut.em.clear();

            DistractorAreaConocimiento found = cut.em.find(DistractorAreaConocimiento.class, disAreaPK);
            assertNotNull(found, "aspirante esta null");
            assertEquals(distractorAreaConocimiento.getDistractorAreaConocimientoPK(), found.getDistractorAreaConocimientoPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("DistractorAreaConocimientoDAOImp.testFindAll");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<DistractorAreaConocimiento> resultList = cut.findAll();
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
        System.out.println("DistractorAreaConocimientoDAOImp.testFindRange");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<DistractorAreaConocimiento> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() >0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("DistractorAreaConocimientoDAOImp.testUpdate");
        String expected = "TEST ACTUALIZADO";
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            distractorAreaConocimiento.setObservaciones(expected);
            DistractorAreaConocimiento entidad = cut.update(distractorAreaConocimiento);
            assertNotNull(entidad);
            assertEquals(expected, entidad.getObservaciones());
            System.out.println(entidad.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("DistractorAreaConocimientoDAOImp.testDelete");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            cut.delete(distractorAreaConocimiento);         
            DistractorAreaConocimiento deleted = cut.findById(disAreaPK);
            assertNull(deleted);
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos > 0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("DistractorAreaConocimientoDAOImp.testCount");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos > 0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("DistractorAreaConocimeintoDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();

        DistractorAreaConocimiento dac = null;
        DistractorAreaConocimientoPK dacPK = new DistractorAreaConocimientoPK(2l, 1);

        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(dac);

        });

        DistractorAreaConocimientoDTO dto1 = cut.toDto(new DistractorAreaConocimiento(dacPK, "nada"));
        DistractorAreaConocimientoDTO dto2 = cut.toDto(new DistractorAreaConocimiento(null, "nada"));
        assertNotNull(dto1);
        assertNotNull(dto2);
        assertNull(dto2.idDistractor());
        assertEquals(0, dto2.idAreaConocimiento());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("DistractorAreaConocimientoDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        DistractorAreaConocimientoDTO dacDTO = null;
        DistractorAreaConocimiento dac = null;
        DistractorAreaConocimiento dac2 = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(dacDTO);

        });
        dac = cut.toEntity(new DistractorAreaConocimientoDTO(1l, 0, "nada"));
        assertNotNull(dac);
        assertEquals(1l, dac.getDistractorAreaConocimientoPK().getIdDistractor());
    }
}
