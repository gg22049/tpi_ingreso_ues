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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacionPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AspiranteIdentificacionDAOImpIT extends ITAbstract {

    AspiranteIdentificacion newAspiranteIdentificacion;
    Aspirante parentAspirante;
    TipoIdentificacion parentTipo;
    AspiranteIdentificacionPK aspIdenPK;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        parentAspirante = new Aspirante(null, "TEST", "RELACION", cal.getTime(), "relacion@test.com");
        parentTipo = new TipoIdentificacion(null, "NIT", null);
        //aspIdenPK = new AspiranteIdentificacionPK();
        newAspiranteIdentificacion = new AspiranteIdentificacion(null, "4444", "IMAGEN NIT", null);
        //newAspiranteIdentificacion.setAspiranteIdentificacionPK(aspIdenPK);
        newAspiranteIdentificacion.setAspirante(parentAspirante);
        newAspiranteIdentificacion.setTipoIdentificacion(parentTipo);
        // newAspiranteIdentificacion=new AspiranteIdentificacion();
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("AspiranteIdentificacionDAOImp.testCreate");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();

            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);

            cut.em.flush();

            aspIdenPK = new AspiranteIdentificacionPK(parentAspirante.getIdAspirante(), parentTipo.getIdTipoIdentificacion());
            newAspiranteIdentificacion.setAspiranteIdentificacionPK(aspIdenPK);

            cut.em.clear();
            cut.create(newAspiranteIdentificacion);
            AspiranteIdentificacionPK pk = newAspiranteIdentificacion.getAspiranteIdentificacionPK();
            assertNotNull(pk);
            AspiranteIdentificacion creado = cut.em.find(AspiranteIdentificacion.class, pk);
            assertNotNull(creado);
            assertEquals("4444", creado.getValor());
            assertEquals(parentAspirante.getCorreo(), creado.getAspirante().getCorreo());
            assertEquals(parentTipo.getNombre(), creado.getTipoIdentificacion().getNombre());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("AspiranteIdentificacionDAOImp.testFindById");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);
            cut.em.flush();

            AspiranteIdentificacionPK pk = new AspiranteIdentificacionPK(
                    parentAspirante.getIdAspirante(),
                    parentTipo.getIdTipoIdentificacion()
            );

            newAspiranteIdentificacion.setAspiranteIdentificacionPK(pk);
            cut.em.persist(newAspiranteIdentificacion);
            cut.em.flush();
            cut.em.clear();

            AspiranteIdentificacion found = cut.em.find(AspiranteIdentificacion.class, pk);

            assertNotNull(pk);
            assertNotNull(pk.getIdAspirante());
            assertNotNull(pk.getIdTipoIdentificacion());
            assertNotNull(found, "aspirante esta null");
            assertEquals(newAspiranteIdentificacion.getAspiranteIdentificacionPK(), found.getAspiranteIdentificacionPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("AspiranteIdentificacionDAOImp.testFindAll");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);
            cut.em.flush();

            AspiranteIdentificacionPK pk = new AspiranteIdentificacionPK(
                    parentAspirante.getIdAspirante(),
                    parentTipo.getIdTipoIdentificacion()
            );

            newAspiranteIdentificacion.setAspiranteIdentificacionPK(pk);
            cut.em.persist(newAspiranteIdentificacion);
            cut.em.flush();
            cut.em.clear();
            List<AspiranteIdentificacion> resultList = cut.findAll();
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
        System.out.println("AspiranteIdentificacionDAOImp.testFindRange");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);
            cut.em.flush();

            AspiranteIdentificacionPK pk = new AspiranteIdentificacionPK(
                    parentAspirante.getIdAspirante(),
                    parentTipo.getIdTipoIdentificacion()
            );

            newAspiranteIdentificacion.setAspiranteIdentificacionPK(pk);
            cut.em.persist(newAspiranteIdentificacion);
            cut.em.flush();
            cut.em.clear();
            List<AspiranteIdentificacion> resultList = cut.findByRange(offset, limit);
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
        System.out.println("AspiranteIdentificacionDAOImp.testUpdate");
        String expected = "imagen nit";
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);
            cut.em.flush();

            AspiranteIdentificacionPK pk = new AspiranteIdentificacionPK(
                    parentAspirante.getIdAspirante(),
                    parentTipo.getIdTipoIdentificacion()
            );

            newAspiranteIdentificacion.setAspiranteIdentificacionPK(pk);
            cut.em.persist(newAspiranteIdentificacion);
            cut.em.flush();
            newAspiranteIdentificacion.setImagenUrl(expected);
            AspiranteIdentificacion entidad = cut.update(newAspiranteIdentificacion);
            assertNotNull(entidad);
            assertEquals(expected, entidad.getImagenUrl());
            System.out.println(entidad.getImagenUrl());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("AspiranteIdentificacionDAOImp.testDelete");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);
            cut.em.flush();

            AspiranteIdentificacionPK pk = new AspiranteIdentificacionPK(
                    parentAspirante.getIdAspirante(),
                    parentTipo.getIdTipoIdentificacion()
            );
            newAspiranteIdentificacion.setAspiranteIdentificacionPK(pk);
            cut.em.persist(newAspiranteIdentificacion);
            cut.em.flush();
            cut.em.clear();

            cut.delete(newAspiranteIdentificacion);
            cut.em.flush();
            cut.em.clear();
            AspiranteIdentificacion deleted = cut.findById(pk);
            assertNotNull(pk.getIdTipoIdentificacion());
            assertNull(deleted);

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("AspiranteIdentificacionDAOImp.testCount");
        AspiranteIdentificacionDAOImp cut = new AspiranteIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(parentAspirante);
            cut.em.persist(parentTipo);
            cut.em.flush();
            cut.em.clear();

            AspiranteIdentificacionPK pk = new AspiranteIdentificacionPK(
                    parentAspirante.getIdAspirante(),
                    parentTipo.getIdTipoIdentificacion()
            );
            newAspiranteIdentificacion.setAspiranteIdentificacionPK(pk);
            cut.em.persist(newAspiranteIdentificacion);
            cut.em.flush();
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
        System.out.println("AspiranteIdentificacionDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        AspiranteIdentificacionDAOImp aspiranteIdentificacionDI = new AspiranteIdentificacionDAOImp();
        AspiranteIdentificacionPK aspiranteIdentificacionPK = new AspiranteIdentificacionPK(1l, 1);
        AspiranteIdentificacionDTO aspiranteIdentificacionDTO;
        AspiranteIdentificacionDTO aspiranteIdentificacionDTO2;
        AspiranteIdentificacion aspiranteIdentificacion = null;
        assertThrows(IllegalStateException.class, () -> {
            aspiranteIdentificacionDI.toDto(aspiranteIdentificacion);
        });
        AspiranteIdentificacion ai = new AspiranteIdentificacion(null, "00000", "imagen", null);
        AspiranteIdentificacion ai2 = new AspiranteIdentificacion(aspiranteIdentificacionPK, "00000", "imagen", null);

        aspiranteIdentificacionDTO = aspiranteIdentificacionDI.toDto(ai);
        assertNotNull(aspiranteIdentificacionDTO);
        aspiranteIdentificacionDTO2 = aspiranteIdentificacionDI.toDto(ai2);
        assertNotNull(aspiranteIdentificacionDTO2);
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("AspiranteIdentificacionDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        AspiranteIdentificacionDAOImp dao = new AspiranteIdentificacionDAOImp();
        AspiranteIdentificacionDTO aspiranteDTO1 = new AspiranteIdentificacionDTO(1L, 1, "00000000", "Imagen", null);

        AspiranteIdentificacion aspiranteIdentificacion;
        AspiranteIdentificacionDTO aspiranteIdentificacionDTONull = null;
        assertThrows(IllegalStateException.class, () -> {
            dao.toEntity(aspiranteIdentificacionDTONull);

        });

        aspiranteIdentificacion = dao.toEntity(aspiranteDTO1);
        assertNotNull(aspiranteIdentificacion);
    }
}
