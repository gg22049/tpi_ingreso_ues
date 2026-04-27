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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaAulaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JornadaAulaDAOImpIT extends ITAbstract {

    JornadaAula newJornadaAula;
    Jornada jornada;

    JornadaAulaPK jorAulaPK;

    private void persistirEscenarioCompleto(EntityManager em) {
        em.persist(jornada);
        em.flush();

        jorAulaPK = new JornadaAulaPK(
                jornada.getIdJornada(),
                "A3"
        );
        newJornadaAula.setJornadaAulaPK(jorAulaPK);
        em.persist(newJornadaAula);
        em.flush();
    }

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        jornada = new Jornada(null, "TEST", cal.getTime(), cal.getTime());

        newJornadaAula = new JornadaAula();
        newJornadaAula.setJornada(jornada);
        newJornadaAula.setObservaciones("Buena Aula");

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("JornadaAulaDAOImp.testCreate");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.em.persist(jornada);
            cut.em.flush();
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            newJornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.clear();
            cut.create(newJornadaAula);
            JornadaAulaPK pk = newJornadaAula.getJornadaAulaPK();
            assertNotNull(pk);
            JornadaAula creado = cut.em.find(JornadaAula.class, pk);
            assertNotNull(creado);
            assertEquals(pk, creado.getJornadaAulaPK());
            assertEquals(newJornadaAula.getObservaciones(), creado.getObservaciones());
            assertNotNull(newJornadaAula.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("JornadaAulaDAOImp.testFindById");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            persistirEscenarioCompleto(cut.em);
            cut.em.clear();

            JornadaAula found = cut.em.find(JornadaAula.class, jorAulaPK);

            assertNotNull(jorAulaPK);
            assertNotNull(found);
            assertEquals(newJornadaAula.getJornadaAulaPK(), found.getJornadaAulaPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("JornadaAulaDAOImp.testFindAll");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<JornadaAula> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() >0);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("JornadaAulaDAOImp.testFindRange");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<JornadaAula> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() >0);
            assertEquals(newJornadaAula, resultList.get(1));

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("JornadaAulaDAOImp.testUpdate");
        String expected = "El Matadero";
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            newJornadaAula.setObservaciones(expected);
            JornadaAula entidad = cut.update(newJornadaAula);
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
        System.out.println("JornadaAulaDAOImp.testDelete");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            cut.delete(newJornadaAula);
            JornadaAula deleted = cut.findById(jorAulaPK);
            assertNull(deleted);

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("JornadaAulaDAOImp.testCount");
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos >0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("JornadaAulaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        JornadaAula ja = null;
        JornadaAulaDTO jaDTO;
        JornadaAulaDTO jaDTO1;

        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(ja);
        });

        jaDTO = cut.toDto(new JornadaAula(new JornadaAulaPK(1l, "A3"), ""));
        assertNotNull(jaDTO);
        assertEquals(1l, jaDTO.idJornada());
        assertEquals("A3", jaDTO.idAula());
        jaDTO1 = cut.toDto(new JornadaAula(null, "No hay"));
        assertNotNull(jaDTO1);
        assertEquals(0l, jaDTO1.idJornada());
        assertEquals("", jaDTO1.idAula());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("JornadaAulaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        JornadaAulaDAOImp cut = new JornadaAulaDAOImp();
        JornadaAula ja;
        JornadaAulaDTO jaDTO = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(jaDTO);

        });

        ja = cut.toEntity(new JornadaAulaDTO(1l, "A3", "El matadero"));
        assertNotNull(ja);
        assertEquals("A3", ja.getJornadaAulaPK().getIdAula());
        assertEquals(1, ja.getJornadaAulaPK().getIdJornada());
    }
}
