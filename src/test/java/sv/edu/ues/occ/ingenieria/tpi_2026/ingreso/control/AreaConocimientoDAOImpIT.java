package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AreaConocimientoDAOImpIT extends ITAbstract {

    AreaConocimiento newEntity;

    @BeforeAll
    void init() {
        newEntity = new AreaConocimiento(-1, "TEST");
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("AreaConocimientoDAOImp.testCreate");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.create(newEntity);
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.create(newEntity);
            cut.em.flush();
            cut.em.clear();
            Integer id = newEntity.getIdAreaConocimiento();
            assertNotNull(id);
            AreaConocimiento created = cut.em.find(AreaConocimiento.class, id);
            assertNotNull(created);
            assertEquals(id, created.getIdAreaConocimiento());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("AreaConocimientoDAOImp.testFindById");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findById(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.findById(1L);
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newEntity);
            cut.em.flush();
            cut.em.clear();
            AreaConocimiento found = cut.findById(newEntity.getIdAreaConocimiento());
            assertNotNull(found);
            assertEquals(found.getIdAreaConocimiento(), newEntity.getIdAreaConocimiento());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("AreaConocimientoDAOImp.testFindAll");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class, () -> {
            cut.findAll();
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newEntity);
            cut.em.flush();
            cut.em.clear();
            List<AreaConocimiento> resultList = cut.findAll();
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
        System.out.println("AreaConocimientoDAOImp.testFindRange");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        int offset = 0;
        int limit = 10;
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findByRange(-limit, limit);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findByRange(limit, offset);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.findByRange(offset, limit);
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newEntity);
            cut.em.flush();
            cut.em.clear();
            List<AreaConocimiento> resultList = cut.findByRange(offset, limit);
            System.out.println(newEntity.getIdAreaConocimiento());
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
        System.out.println("AreaConocimientoDAOImp.testUpdate");
        String expected = "test";
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.update(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.update(newEntity);
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newEntity);
            cut.em.flush();
            cut.em.clear();
            newEntity.setNombre(expected);
            AreaConocimiento updated = cut.update(newEntity);
            assertEquals(updated.getNombre(), expected);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("AreaConocimientoDAOImp.testDelete");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.delete(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.delete(newEntity);
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newEntity);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newEntity);
            cut.em.flush();
            cut.em.clear();
            AreaConocimiento deleted = cut.em.find(AreaConocimiento.class, newEntity.getIdAreaConocimiento());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("AreaConocimientoDAOImp.testCount");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class, () -> {
            cut.count();
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newEntity);
            cut.em.flush();
            cut.em.clear();
            Long counted = cut.count();
            assertNotNull(counted);
            assertTrue(counted > 0L);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

}
