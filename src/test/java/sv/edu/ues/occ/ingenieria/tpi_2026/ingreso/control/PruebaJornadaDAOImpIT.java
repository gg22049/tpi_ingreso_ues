/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityTransaction;
import java.math.BigDecimal;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaJornadaDAOImpIT extends ITAbstract {

    PruebaJornada newPruebaJornada;
    Prueba prueba;
    TipoPrueba tipoPrueba;
    Jornada jornada;
    PruebaJornadaPK pueJorPK;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal puntaje = new BigDecimal("100.0");
        tipoPrueba = new TipoPrueba(null, "Examen Presencial", true, "Prueba presencial, a lapiz y papel");
        prueba = new Prueba(null, "prueba 2027", puntaje, puntaje, null);
        prueba.setIndicaciones("trabajar de forma ordenada");
        prueba.setDuracion(100);
        prueba.setIdTipoPrueba(tipoPrueba);

        jornada = new Jornada(null, "Primera Jornada Nuevo Ingreso 2027", cal.getTime(), cal.getTime(), "Llegar 30 minutos antes.");
        newPruebaJornada = new PruebaJornada(null, null, "TEST OBSERVACIONES");
        newPruebaJornada.setPrueba(prueba);
        newPruebaJornada.setJornada(jornada);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaJorndaDAOImp.testCreate");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            pueJorPK = new PruebaJornadaPK(prueba.getIdPrueba(), jornada.getIdJornada());
            newPruebaJornada.setPruebaJornadaPK(pueJorPK);
            cut.em.clear();
            cut.create(newPruebaJornada);
            PruebaJornadaPK pk = newPruebaJornada.getPruebaJornadaPK();
            assertNotNull(pk);
            PruebaJornada creado = cut.em.find(PruebaJornada.class, pk);
            assertNotNull(creado);
            assertEquals("TEST OBSERVACIONES", creado.getObservaciones());
            assertEquals(newPruebaJornada.getPruebaJornadaPK(), creado.getPruebaJornadaPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PruebaJornadaDAOImp.testFindById");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            PruebaJornadaPK pk = new PruebaJornadaPK(
                    prueba.getIdPrueba(),
                    jornada.getIdJornada()
            );
            newPruebaJornada.setPruebaJornadaPK(pk);
            cut.em.persist(newPruebaJornada);
            cut.em.flush();
            cut.em.clear();
            PruebaJornada found = cut.em.find(PruebaJornada.class, pk);
            assertNotNull(found);
            assertEquals(newPruebaJornada.getPruebaJornadaPK(), found.getPruebaJornadaPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaJornadaDAOImp.testFindAll");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            PruebaJornadaPK pk = new PruebaJornadaPK(
                    prueba.getIdPrueba(),
                    jornada.getIdJornada()
            );
            newPruebaJornada.setPruebaJornadaPK(pk);
            cut.em.persist(newPruebaJornada);
            cut.em.flush();
            cut.em.clear();
            List<PruebaJornada> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() == 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("PruebaJornadaDAOImp.testFindRange");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            PruebaJornadaPK pk = new PruebaJornadaPK(
                    prueba.getIdPrueba(),
                    jornada.getIdJornada()
            );
            newPruebaJornada.setPruebaJornadaPK(pk);
            cut.em.persist(newPruebaJornada);
            cut.em.flush();
            cut.em.clear();
            List<PruebaJornada> resultList = cut.findByRange(offset, limit);
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
        System.out.println("PruebaJornadaDAOImp.testUpdate");
        String expected = "TEST SIN OBSERVACIONES";
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            PruebaJornadaPK pk = new PruebaJornadaPK(
                    prueba.getIdPrueba(),
                    jornada.getIdJornada()
            );
            newPruebaJornada.setPruebaJornadaPK(pk);
            cut.em.persist(newPruebaJornada);
            cut.em.flush();
            newPruebaJornada.setObservaciones(expected);
            PruebaJornada entidad = cut.update(newPruebaJornada);
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
        System.out.println("PruebaJornadaDAOImp.testDelete");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            PruebaJornadaPK pk = new PruebaJornadaPK(
                    prueba.getIdPrueba(),
                    jornada.getIdJornada()
            );
            newPruebaJornada.setPruebaJornadaPK(pk);
            cut.em.persist(newPruebaJornada);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newPruebaJornada);
            cut.em.flush();
            cut.em.clear();
            PruebaJornada deleted = cut.findById(pk);
            assertNull(deleted);
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos == 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("PruebaJornadaDAOImp.testCount");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.persist(jornada);
            cut.em.flush();
            cut.em.clear();
            PruebaJornadaPK pk = new PruebaJornadaPK(
                    prueba.getIdPrueba(),
                    jornada.getIdJornada()
            );
            newPruebaJornada.setPruebaJornadaPK(pk);
            cut.em.persist(newPruebaJornada);
            cut.em.flush();
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos == 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("PruebaJornadaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        PruebaJornada pj = null;
        PruebaJornadaDTO pjDTO;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pj);
        });
        pjDTO = cut.toDto(new PruebaJornada(new PruebaJornadaPK(1, 1), cal.getTime(), "Se podria cambiar la jornada de esta prueba"));
        assertNotNull(pjDTO);
        assertEquals(1, pjDTO.idJornada());
        assertEquals(1, pjDTO.idPrueba());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaJornadaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaJornadaDAOImp cut = new PruebaJornadaDAOImp();
        PruebaJornadaDTO tiDTO = null;
        PruebaJornada ti1;
        PruebaJornada ti2;
        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(tiDTO);
        });
        ti1 = cut.toEntity(new PruebaJornadaDTO(1, 1, cal.getTime(), "Se podria cambiar la jornada de esta prueba"));
        assertNotNull(ti1);
        assertEquals(cal.getTime(), ti1.getFechaCreacion());
        
    }
}
