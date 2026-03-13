/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class AreaConocimientoDAOImpTest {

    @Mock
    EntityManager emMock;

    List<AreaConocimiento> areaList;

    public AreaConocimientoDAOImpTest() {
        AreaConocimiento areaWid = new AreaConocimiento();
        areaWid.setIdAreaConocimiento(1);
        areaList = Arrays.asList(areaWid, new AreaConocimiento(), new AreaConocimiento());
    }

    @Test
    void createTest() {
        System.out.println("AreaConocimientoDAOImpTest.createTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.create(null);
                });
        AreaConocimiento area = new AreaConocimiento();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.create(area);
                });
        cut.em = emMock;
        cut.create(area);
        verify(emMock).persist(area);
    }

    @Test
    void updateTest() {
        System.out.println("AreaConocimientoDAOImpTest.updateTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.update(null);
                });
        AreaConocimiento area = new AreaConocimiento();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.update(area);
                });
        cut.em = emMock;
        when(emMock.merge(area)).thenReturn(area);
        cut.update(area);
        verify(emMock).merge(area);
    }

    @Test
    void findAllTest() {
        System.out.println("AreaConocimientoDAOImpTest.findAllTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.findAll();
                });
        cut.em = emMock;
        CriteriaBuilder cbMock = mock(CriteriaBuilder.class);
        CriteriaQuery<AreaConocimiento> cqMock = mock(CriteriaQuery.class);
        Root rMock = mock(Root.class);
        TypedQuery<AreaConocimiento> tqMock = mock(TypedQuery.class);
        when(emMock.getCriteriaBuilder()).thenReturn(cbMock);
        when(cbMock.createQuery(AreaConocimiento.class)).thenReturn(cqMock);
        when(cqMock.from(AreaConocimiento.class)).thenReturn(rMock);
        when(emMock.createQuery(cqMock)).thenReturn(tqMock);
        when(tqMock.getResultList()).thenReturn(areaList);
        List<AreaConocimiento> resultList = cut.findAll();
        assertNotNull(resultList);
        assertEquals(areaList.size(), resultList.size());
    }

    @Test
    void findByIdTest() {
        System.out.println("AreaConocimientoDAOImpTest.findByIdTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        Integer id = 1;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.findById(null);
                });
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.findById(id);
                });
        cut.em = emMock;
        AreaConocimiento expected = new AreaConocimiento();
        expected.setIdAreaConocimiento(id);
        when(emMock.find(AreaConocimiento.class, id)).thenReturn(expected);
        AreaConocimiento found = cut.findById(id);
        assertNotNull(expected);
        assertEquals(expected.getIdAreaConocimiento(), expected.getIdAreaConocimiento());
    }

    @Test
    void findByRangeTest() {
        System.out.println("AreaConocimientoDAOImpTest.findByRangeTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        Integer offset = 1;
        Integer limit = 10;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.findByRange(10, 1);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.findByRange(-1, -10);
                });
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.findByRange(1, 10);
                });
        cut.em = emMock;
        CriteriaBuilder cbMock = mock(CriteriaBuilder.class);
        CriteriaQuery<AreaConocimiento> cqMock = mock(CriteriaQuery.class);
        TypedQuery<AreaConocimiento> tqMock = mock(TypedQuery.class);
        Root rMock = mock(Root.class);
        when(emMock.getCriteriaBuilder()).thenReturn(cbMock);
        when(cbMock.createQuery(AreaConocimiento.class)).thenReturn(cqMock);
        when(cqMock.from(AreaConocimiento.class)).thenReturn(rMock);
        when(emMock.createQuery(cqMock)).thenReturn(tqMock);
        when(tqMock.getResultList()).thenReturn(areaList);
        List<AreaConocimiento> resultList = cut.findByRange(1, 10);
        assertNotNull(resultList);
        assertEquals(areaList.size(), resultList.size());
    }

    @Test
    void deleteTest() {
        System.out.println("AreaConocimientoDAOImpTest.deleteTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cut.delete(null);
                });
        AreaConocimiento area = new AreaConocimiento();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.delete(area);
                });
        cut.em = emMock;
        when(emMock.contains(area)).thenReturn(false);
        when(emMock.merge(area)).thenReturn(area);
        cut.delete(area);
        when(emMock.contains(area)).thenReturn(true);
        cut.delete(area);
        verify(emMock, times(2)).remove(area);
    }

    @Test
    void countTest() {
        System.out.println("AreaConocimientoDAOImpTest.countTest");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.count();
                });
        cut.em = emMock;
        CriteriaBuilder cbMock = mock(CriteriaBuilder.class);
        CriteriaQuery<Long> cqMock = mock(CriteriaQuery.class);
        Root rMock = mock(Root.class);
        TypedQuery<Long> tqMock = mock(TypedQuery.class);
        when(emMock.getCriteriaBuilder()).thenReturn(cbMock);
        when(cbMock.createQuery(Long.class)).thenReturn(cqMock);
        when(cqMock.from(AreaConocimiento.class)).thenReturn(rMock);
        when(emMock.createQuery(cqMock)).thenReturn(tqMock);
        Integer size = areaList.size();
        Long sizeExpected = size.longValue();
        when(tqMock.getSingleResult()).thenReturn(sizeExpected);
        Long result = cut.count();
        assertNotNull(result);
        assertEquals(sizeExpected, result);
    }

}
