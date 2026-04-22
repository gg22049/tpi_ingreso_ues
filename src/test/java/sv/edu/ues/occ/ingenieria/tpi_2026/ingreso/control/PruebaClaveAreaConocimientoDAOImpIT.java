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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaClaveAreaConocimientoDAOImpIT extends ITAbstract {

    PruebaClaveAreaConocimiento pcac;
    PruebaClaveAreaConocimientoPK pcacPK;

    Prueba prueba;
    PruebaClave pruebaClave;
    TipoPrueba tipoPrueba;
    AreaConocimiento areaConocimiento;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("100");
        areaConocimiento = new AreaConocimiento(null, "MATE", "matematica avanzada", true, null);

        prueba = new Prueba(null, "Prueba 2026", "Trabajar sin ayuda", decimal, decimal, 100, null, null);
        pruebaClave = new PruebaClave(null, "Clave A");
        tipoPrueba = new TipoPrueba(null, "En linea", true, "Suerte");
        pcac = new PruebaClaveAreaConocimiento(null, 25, decimal);
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaClaveAreaConocimientoImp.testCreate");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {

            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();

            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.create(pcac);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimiento entidad = cut.findById(pcacPK);
            assertNotNull(entidad);
            assertEquals(pcacPK, entidad.getPruebaClaveAreaConocimientoPK());
            System.out.println("-----------------------------------");
            System.out.println(entidad.getCantidad());
        } finally {
            tx.rollback();
            cut.em.close();

        }
    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PruebaClaveAreaConocimientoDAOImp.testFindById");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();
            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimiento entidad = cut.findById(pcacPK);
            assertNotNull(entidad);
            assertEquals(pcacPK, entidad.getPruebaClaveAreaConocimientoPK());

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaClaveAreaConocimientoDAOImp.testFindAll");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();
            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();
            cut.em.clear();
            List<PruebaClaveAreaConocimiento> resultList = cut.findAll();
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
        System.out.println("PruebaClaveAreaConocimientoDAOImp.testFindRange");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();
            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();
            cut.em.clear();
            List<PruebaClaveAreaConocimiento> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() == 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("PruebaClaveAreaConocimientoDAOImp.testUpdate");
        int expected = 30;
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();
            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();
            cut.em.clear();
            pcac.setCantidad(expected);
            PruebaClaveAreaConocimiento entidad = cut.update(pcac);
            assertNotNull(entidad);
            assertEquals(expected, entidad.getCantidad());
            System.out.println(entidad.getCantidad());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("PruebaClaveAreaConocimientoDAOImp.testDelete");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {

            tx.begin();
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();
            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.delete(pcac);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimiento deleted = cut.findById(pcacPK);
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
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testCount");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos == 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("PruebaClaveAreaConocimeintoDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        PruebaClaveAreaConocimiento pcac = null;
        PruebaClaveAreaConocimientoDTO pcacDTO;
        PruebaClaveAreaConocimientoDTO pcacDTO1;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pcac);

        });
        pcacDTO = cut.toDto(new PruebaClaveAreaConocimiento(new PruebaClaveAreaConocimientoPK(1l, 1), 1, decimal));
        pcacDTO1 = cut.toDto(new PruebaClaveAreaConocimiento(null, 1, decimal));

        assertNotNull(pcacDTO);
        assertNotNull(pcacDTO.idPruebaClave());
        assertNotNull(pcacDTO.idAreaConocimiento());
        assertNotNull(pcacDTO1);
        assertEquals(0, pcacDTO1.idAreaConocimiento());
        assertEquals(0, pcacDTO1.idPruebaClave());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaClaveAreaConocimeintoDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
         BigDecimal decimal = new BigDecimal("34");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        PruebaClaveAreaConocimientoDTO pcacDTO = null;
        PruebaClaveAreaConocimiento pcacp;
        PruebaClaveAreaConocimiento pcacp1;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pcacDTO);

        });
        pcacp = cut.toEntity(new PruebaClaveAreaConocimientoDTO(1l, 1, 15, decimal));
        assertNotNull(pcacp);

    }

}
