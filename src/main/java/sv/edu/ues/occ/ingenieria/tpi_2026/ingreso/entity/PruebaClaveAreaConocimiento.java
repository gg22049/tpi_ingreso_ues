/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "prueba_clave_area_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaClaveAreaConocimiento.findAll", query = "SELECT p FROM PruebaClaveAreaConocimiento p"),
    @NamedQuery(name = "PruebaClaveAreaConocimiento.findByIdPruebaClave", query = "SELECT p FROM PruebaClaveAreaConocimiento p WHERE p.pruebaClaveAreaConocimientoPK.idPruebaClave = :idPruebaClave"),
    @NamedQuery(name = "PruebaClaveAreaConocimiento.findByIdAreaConocimiento", query = "SELECT p FROM PruebaClaveAreaConocimiento p WHERE p.pruebaClaveAreaConocimientoPK.idAreaConocimiento = :idAreaConocimiento"),
    @NamedQuery(name = "PruebaClaveAreaConocimiento.findByCantidad", query = "SELECT p FROM PruebaClaveAreaConocimiento p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PruebaClaveAreaConocimiento.findByPorcentaje", query = "SELECT p FROM PruebaClaveAreaConocimiento p WHERE p.porcentaje = :porcentaje")})
public class PruebaClaveAreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaClaveAreaConocimientoPK pruebaClaveAreaConocimientoPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreaConocimiento areaConocimiento;
    @JoinColumn(name = "id_prueba_clave", referencedColumnName = "id_prueba_clave", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PruebaClave pruebaClave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pruebaClaveAreaConocimiento", fetch = FetchType.LAZY)
    private List<PruebaClaveAreaConocimientoPregunta> pruebaClaveAreaConocimientoPreguntaList;

    public PruebaClaveAreaConocimiento() {
    }

    public PruebaClaveAreaConocimiento(PruebaClaveAreaConocimientoPK pruebaClaveAreaConocimientoPK) {
        this.pruebaClaveAreaConocimientoPK = pruebaClaveAreaConocimientoPK;
    }

    public PruebaClaveAreaConocimiento(long idPruebaClave, int idAreaConocimiento) {
        this.pruebaClaveAreaConocimientoPK = new PruebaClaveAreaConocimientoPK(idPruebaClave, idAreaConocimiento);
    }

    public PruebaClaveAreaConocimiento(PruebaClaveAreaConocimientoPK pruebaClaveAreaConocimientoPK, Integer cantidad, BigDecimal porcentaje) {
        this.pruebaClaveAreaConocimientoPK = pruebaClaveAreaConocimientoPK;
        this.cantidad = cantidad;
        this.porcentaje = porcentaje;
    }

    public PruebaClaveAreaConocimientoPK getPruebaClaveAreaConocimientoPK() {
        return pruebaClaveAreaConocimientoPK;
    }

    public void setPruebaClaveAreaConocimientoPK(PruebaClaveAreaConocimientoPK pruebaClaveAreaConocimientoPK) {
        this.pruebaClaveAreaConocimientoPK = pruebaClaveAreaConocimientoPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public AreaConocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public PruebaClave getPruebaClave() {
        return pruebaClave;
    }

    public void setPruebaClave(PruebaClave pruebaClave) {
        this.pruebaClave = pruebaClave;
    }

    @XmlTransient
    public List<PruebaClaveAreaConocimientoPregunta> getPruebaClaveAreaConocimientoPreguntaList() {
        return pruebaClaveAreaConocimientoPreguntaList;
    }

    public void setPruebaClaveAreaConocimientoPreguntaList(List<PruebaClaveAreaConocimientoPregunta> pruebaClaveAreaConocimientoPreguntaList) {
        this.pruebaClaveAreaConocimientoPreguntaList = pruebaClaveAreaConocimientoPreguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaClaveAreaConocimientoPK != null ? pruebaClaveAreaConocimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaClaveAreaConocimiento)) {
            return false;
        }
        PruebaClaveAreaConocimiento other = (PruebaClaveAreaConocimiento) object;
        if ((this.pruebaClaveAreaConocimientoPK == null && other.pruebaClaveAreaConocimientoPK != null) || (this.pruebaClaveAreaConocimientoPK != null && !this.pruebaClaveAreaConocimientoPK.equals(other.pruebaClaveAreaConocimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento[ pruebaClaveAreaConocimientoPK=" + pruebaClaveAreaConocimientoPK + " ]";
    }

}
