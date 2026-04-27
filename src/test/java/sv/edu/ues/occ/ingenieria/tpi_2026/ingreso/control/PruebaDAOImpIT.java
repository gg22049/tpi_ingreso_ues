/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaDAOImpIT extends ITAbstract {

    Prueba newPrueba;
    TipoPrueba parentTipo;

    private void persistirEscenarioCompleto(EntityManager em) {
        em.persist(parentTipo);
        em.flush();
        newPrueba.setIdTipoPrueba(parentTipo);
        em.persist(newPrueba);
        em.flush();
    }

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("100");
        parentTipo = new TipoPrueba(null, "Examen Presencial", true, "Prueba presencial, no puede ser realizada en otro "
                + "lugar que no se la UES");
        newPrueba = new Prueba(null, "prueba 2027", decimal, decimal, null);
        newPrueba.setIndicaciones("trabajar de forma ordenada");
        newPrueba.setDuracion(100);
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaDAOImp.testCreate");
        PruebaDAOImp cut = new PruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.em.persist(parentTipo);
            cut.em.flush();
            cut.em.clear();
            newPrueba.setIdTipoPrueba(parentTipo);
            cut.create(newPrueba);
            cut.em.flush();
            Prueba creado = cut.em.find(Prueba.class, newPrueba.getIdPrueba());
            assertNotNull(creado);
            assertEquals("prueba 2027", creado.getNombre());
            assertEquals(newPrueba.getIdPrueba(), creado.getIdPrueba());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PruebaDAOImp.testFindById");
        PruebaDAOImp cut = new PruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            Prueba found = cut.em.find(Prueba.class, newPrueba.getIdPrueba());
            assertNotNull(found);
            assertEquals(newPrueba.getIdPrueba(), found.getIdPrueba());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaImp.testFindAll");
        PruebaDAOImp cut = new PruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<Prueba> resultList = cut.findAll();
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
        System.out.println("PruebaDAOImp.testFindRange");
        PruebaDAOImp cut = new PruebaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<Prueba> resultList = cut.findByRange(offset, limit);
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
        System.out.println("PruebaDAOImp.testUpdate");
        String expected = "prueba 2028";
        PruebaDAOImp cut = new PruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            newPrueba.setNombre(expected);
            newPrueba = cut.update(newPrueba);
            cut.em.flush();
            assertNotNull(newPrueba);
            assertEquals(expected, newPrueba.getNombre());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("PruebaDAOImp.testDelete");
        PruebaDAOImp cut = new PruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            assertNotNull(parentTipo.getIdTipoPrueba());
            cut.delete(newPrueba);
            Prueba deleted = cut.findById(newPrueba.getIdPrueba());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("PruebaDAOImp.testCount");
        PruebaDAOImp cut = new PruebaDAOImp();
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
        System.out.println("PruebaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("33");
        PruebaDAOImp cut = new PruebaDAOImp();
        Prueba prueba = null;
        PruebaDTO pruebaDTO;
        PruebaDTO pruebaDTO2;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(prueba);

        });

        pruebaDTO = cut.toDto(new Prueba(1l, "examen", "no hay", decimal, decimal, 100, cal.getTime(), new TipoPrueba(1)));
        assertNotNull(pruebaDTO);
        pruebaDTO2 = cut.toDto(new Prueba(1l, "examen", "no hay", decimal, decimal, 100, cal.getTime(), null));
        assertNotNull(pruebaDTO2);
        assertNull(pruebaDTO2.idTipoPrueba());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("33");
        PruebaDAOImp cut = new PruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            Prueba prueba;
            Prueba prueba2;
            PruebaDTO pruebaDTO = null;
            assertThrows(IllegalStateException.class, () -> {
                cut.toEntity(pruebaDTO);

            });
            PruebaDTO dto = new PruebaDTO(1l, "jornada 2018", "nada", decimal, decimal, 34, cal.getTime(), 1);
            prueba = cut.toEntity(dto);
            assertNotNull(prueba);
            prueba2 = cut.toEntity(new PruebaDTO(1l, "jornada 2018", "nada", decimal, decimal, 34, cal.getTime(), null));
            assertNotNull(prueba2);
            assertNull(prueba2.getIdTipoPrueba());
        } finally {
            tx.rollback();
            cut.em.close();

        }

    }

}
