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
import static sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.ITAbstract.emf;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaClaveAreaConocimientoPreguntaDAOImpIT extends ITAbstract{
        PruebaClaveAreaConocimientoPregunta pcacP;
    PruebaClaveAreaConocimientoPreguntaPK pcacPPK;
    
    PruebaClaveAreaConocimiento pcac;
    PruebaClaveAreaConocimientoPK pcacPK;

    Prueba prueba;
    PruebaClave pruebaClave;
    TipoPrueba tipoPrueba;
    AreaConocimiento areaConocimiento;
    
    Pregunta pregunta;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("100");
        pregunta=new Pregunta(null, "Vamos a llegar al mundial alguna vez?", true, "imagen", "NUNCA");
        
        areaConocimiento = new AreaConocimiento(null, "MATE", "matematica avanzada", true, null);

        prueba = new Prueba(null, "Prueba 2026", "Trabajar sin ayuda", decimal, decimal, 100, null, null);
        pruebaClave = new PruebaClave(null, "Clave A");
        tipoPrueba = new TipoPrueba(null, "En linea", true, "Suerte");
        pcac = new PruebaClaveAreaConocimiento(null, 25, decimal);
        
        pcacP=new PruebaClaveAreaConocimientoPregunta(null, decimal);
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaImp.testCreate");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {

            tx.begin();
            cut.em.persist(pregunta);
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
            
            pcacPPK=new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.create(pcacP);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimientoPregunta entidad = cut.findById(pcacPPK);
            assertNotNull(entidad);
            assertEquals(pcacPPK, entidad.getPruebaClaveAreaConocimientoPreguntaPK());
            System.out.println("-----------------------------------");
            System.out.println(entidad.getPorcentaje());
        } finally {
            tx.rollback();
            cut.em.close();

        }
    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testFindById");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(pregunta);
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
            
            pcacPPK=new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimientoPregunta entidad = cut.findById(pcacPPK);
            assertNotNull(entidad);
            assertEquals(pcacPPK, entidad.getPruebaClaveAreaConocimientoPreguntaPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testFindAll");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
           tx.begin();
            cut.em.persist(pregunta);
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
            
            pcacPPK=new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            cut.em.clear();
            List<PruebaClaveAreaConocimientoPregunta> resultList = cut.findAll();
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
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testFindRange");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();        
            List<PruebaClaveAreaConocimientoPregunta> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() == 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testUpdate");
          BigDecimal expected = new BigDecimal("20");
        
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(pregunta);
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
            
            pcacPPK=new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            cut.em.clear();
            pcacP.setPorcentaje(expected);
            PruebaClaveAreaConocimientoPregunta entidad = cut.update(pcacP);
            assertNotNull(entidad);
            assertEquals(expected, entidad.getPorcentaje());
            System.out.println(entidad.getPorcentaje());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testDelete");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {

            tx.begin();
            cut.em.persist(pregunta);
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
            
            pcacPPK=new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            cut.em.clear();
            cut.delete(pcacP);
            PruebaClaveAreaConocimientoPregunta deleted = cut.findById(pcacPPK);
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
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testCount");
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
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
//PruebaClaveAreaConocimientoPreguntaDAOImp
    
     @Test
    @Order(8)
    void testToDto() {
        System.out.println("PruebaClaveAreaConocimeintoPreguntaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        PruebaClaveAreaConocimientoPregunta pcacp = null;
        PruebaClaveAreaConocimientoPreguntaDTO pcacpDTO;
        PruebaClaveAreaConocimientoPreguntaDTO pcacpDTO1;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pcacp);

        });
        pcacpDTO = cut.toDto(new PruebaClaveAreaConocimientoPregunta(new PruebaClaveAreaConocimientoPreguntaPK(1l, 1, 1l),
                new BigDecimal("34")
        ));
        //pcacpDTO1=cut.toDto(new PruebaClaveAreaConocimientoPregunta(null, new BigDecimal("23"));
        
        assertNotNull(pcacpDTO);
         assertEquals(1l, pcacpDTO.idPruebaClave());
       // assertNotNull(pcacpDTO1);
        //assertNotNull(pcacpDTO1.idDistractor());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaClaveAreaConocimeintoPreguntaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaClaveAreaConocimientoPreguntaDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDAOImp();
        PruebaClaveAreaConocimientoPreguntaDTO pcacpDTO = null;
        PruebaClaveAreaConocimientoPregunta pcacp;
        PruebaClaveAreaConocimientoPregunta pcacp1;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pcacpDTO);

        });
        pcacp = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDTO(1l, 1, 1l, new BigDecimal("23")));
        assertNotNull(pcacp);
        //pcacpd1 = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDistractorDTO(0, 0, 0, 0, cal.getTime(), "nada"));
        //assertNotNull(pcacpd1);
        //assertNull(pcacpd1.getDistractor());

    }

}
