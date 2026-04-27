/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaClaveDAOImpIT extends ITAbstract {

    PruebaClave pruebaClave;
    Prueba prueba;
    TipoPrueba tipoPrueba;

    private void persistirEscenarioCompleto(EntityManager em) {
        em.persist(tipoPrueba);
        em.flush();
        em.persist(prueba);
        em.flush();
        pruebaClave.setIdPrueba(prueba);
        em.persist(pruebaClave);
        em.flush();
    }

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("100.0");
        tipoPrueba = new TipoPrueba(null, "Presencial", true, null);
        prueba = new Prueba(null, "prueba 2027", decimal, decimal, null);
        prueba.setIndicaciones("trabajar de forma ordenada");
        prueba.setDuracion(100);
        prueba.setIdTipoPrueba(tipoPrueba);
        pruebaClave = new PruebaClave(null, "Clave dificil", null);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaClaveDAOImp.testCreate");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.flush();
            cut.em.persist(prueba);
            cut.em.flush();
            cut.em.clear();
            pruebaClave.setIdPrueba(prueba);
            cut.create(pruebaClave);
            cut.em.flush();
            PruebaClave creado = cut.em.find(PruebaClave.class, pruebaClave.getIdPruebaClave());
            assertNotNull(creado);
            assertEquals("Clave dificil", creado.getNombre());
            assertEquals(pruebaClave, creado);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PruebaClaveDAOImp.testFindById");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            PruebaClave found = cut.em.find(PruebaClave.class, pruebaClave.getIdPruebaClave());
            assertNotNull(found);
            assertEquals(pruebaClave.getIdPruebaClave(), found.getIdPruebaClave());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaClaveDAOImp.testFindAll");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<PruebaClave> resultList = cut.findAll();
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
        System.out.println("PruebaClaveDAOImp.testFindRange");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<PruebaClave> resultList = cut.findByRange(offset, limit);
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
        System.out.println("PruebaClaveDAOImp.testUpdate");
        String expected = "Prueba 2028";
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);

            pruebaClave.setNombre(expected);
            pruebaClave = cut.update(pruebaClave);
            cut.em.flush();
            PruebaClave comprobar = cut.findById(pruebaClave.getIdPruebaClave());
            assertNotNull(comprobar);
            assertEquals(expected, pruebaClave.getNombre());

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("PruebaClaveDAOImp.testDelete");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            cut.delete(pruebaClave);
            PruebaClave deleted = cut.findById(pruebaClave.getIdPruebaClave());
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
        System.out.println("PruebaClaveDAOImp.testCount");
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
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
        System.out.println("AspiranteOpcionDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        PruebaClaveDTO pcDTO;
        PruebaClave pc2 = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pc2);

        });

        pcDTO = cut.toDto(new PruebaClave(1l, "calve 1", new Prueba(1l)));
        assertNotNull(pcDTO);
        PruebaClaveDTO aspiDTO = cut.toDto(new PruebaClave(1l, "calve 1", null));
        assertNotNull(aspiDTO);
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaClaveDAOImp.testToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaClaveDAOImp cut = new PruebaClaveDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            PruebaClaveDTO pcDTO1 = new PruebaClaveDTO(1l, "clave 1", 1l);
            PruebaClaveDTO pcDTO2 = new PruebaClaveDTO(2l, "I300515", null);
            PruebaClave pc1;
            PruebaClave pc2;
            PruebaClaveDTO pc = null;
            assertThrows(IllegalStateException.class, () -> {
                cut.toEntity(pc);

            });

            pc1 = cut.toEntity(pcDTO1);
            assertNotNull(pc1);
            pc2 = cut.toEntity(pcDTO2);
            assertNotNull(pc2);
            assertNull(pc2.getIdPrueba());
        } finally {

            cut.em.close();
        }

    }
}
