/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "prueba_clave_area_conocimiento_pregunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaClaveAreaConocimientoPregunta.findAll", query = "SELECT p FROM PruebaClaveAreaConocimientoPregunta p"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPregunta.findByIdPruebaClave", query = "SELECT p FROM PruebaClaveAreaConocimientoPregunta p WHERE p.pruebaClaveAreaConocimientoPreguntaPK.idPruebaClave = :idPruebaClave"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPregunta.findByIdAreaConocimiento", query = "SELECT p FROM PruebaClaveAreaConocimientoPregunta p WHERE p.pruebaClaveAreaConocimientoPreguntaPK.idAreaConocimiento = :idAreaConocimiento"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPregunta.findByIdPregunta", query = "SELECT p FROM PruebaClaveAreaConocimientoPregunta p WHERE p.pruebaClaveAreaConocimientoPreguntaPK.idPregunta = :idPregunta"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPregunta.findByPorcentaje", query = "SELECT p FROM PruebaClaveAreaConocimientoPregunta p WHERE p.porcentaje = :porcentaje")})
public class PruebaClaveAreaConocimientoPregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaClaveAreaConocimientoPreguntaPK pruebaClaveAreaConocimientoPreguntaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pruebaClaveAreaConocimientoPregunta", fetch = FetchType.LAZY)
    private List<PruebaClaveAreaConocimientoPreguntaDistractor> pruebaClaveAreaConocimientoPreguntaDistractorList;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta pregunta;
    @JoinColumns({
        @JoinColumn(name = "id_prueba_clave", referencedColumnName = "id_prueba_clave", insertable = false, updatable = false),
        @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PruebaClaveAreaConocimiento pruebaClaveAreaConocimiento;

    public PruebaClaveAreaConocimientoPregunta() {
    }

    public PruebaClaveAreaConocimientoPregunta(PruebaClaveAreaConocimientoPreguntaPK pruebaClaveAreaConocimientoPreguntaPK) {
        this.pruebaClaveAreaConocimientoPreguntaPK = pruebaClaveAreaConocimientoPreguntaPK;
    }

    public PruebaClaveAreaConocimientoPregunta(PruebaClaveAreaConocimientoPreguntaPK pruebaClaveAreaConocimientoPreguntaPK, BigDecimal porcentaje) {
        this.pruebaClaveAreaConocimientoPreguntaPK = pruebaClaveAreaConocimientoPreguntaPK;
        this.porcentaje = porcentaje;
    }

    public PruebaClaveAreaConocimientoPregunta(long idPruebaClave, int idAreaConocimiento, long idPregunta) {
        this.pruebaClaveAreaConocimientoPreguntaPK = new PruebaClaveAreaConocimientoPreguntaPK(idPruebaClave, idAreaConocimiento, idPregunta);
    }

    public PruebaClaveAreaConocimientoPreguntaPK getPruebaClaveAreaConocimientoPreguntaPK() {
        return pruebaClaveAreaConocimientoPreguntaPK;
    }

    public void setPruebaClaveAreaConocimientoPreguntaPK(PruebaClaveAreaConocimientoPreguntaPK pruebaClaveAreaConocimientoPreguntaPK) {
        this.pruebaClaveAreaConocimientoPreguntaPK = pruebaClaveAreaConocimientoPreguntaPK;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    @XmlTransient
    public List<PruebaClaveAreaConocimientoPreguntaDistractor> getPruebaClaveAreaConocimientoPreguntaDistractorList() {
        return pruebaClaveAreaConocimientoPreguntaDistractorList;
    }

    public void setPruebaClaveAreaConocimientoPreguntaDistractorList(List<PruebaClaveAreaConocimientoPreguntaDistractor> pruebaClaveAreaConocimientoPreguntaDistractorList) {
        this.pruebaClaveAreaConocimientoPreguntaDistractorList = pruebaClaveAreaConocimientoPreguntaDistractorList;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public PruebaClaveAreaConocimiento getPruebaClaveAreaConocimiento() {
        return pruebaClaveAreaConocimiento;
    }

    public void setPruebaClaveAreaConocimiento(PruebaClaveAreaConocimiento pruebaClaveAreaConocimiento) {
        this.pruebaClaveAreaConocimiento = pruebaClaveAreaConocimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaClaveAreaConocimientoPreguntaPK != null ? pruebaClaveAreaConocimientoPreguntaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaClaveAreaConocimientoPregunta)) {
            return false;
        }
        PruebaClaveAreaConocimientoPregunta other = (PruebaClaveAreaConocimientoPregunta) object;
        if ((this.pruebaClaveAreaConocimientoPreguntaPK == null && other.pruebaClaveAreaConocimientoPreguntaPK != null) || (this.pruebaClaveAreaConocimientoPreguntaPK != null && !this.pruebaClaveAreaConocimientoPreguntaPK.equals(other.pruebaClaveAreaConocimientoPreguntaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta[ pruebaClaveAreaConocimientoPreguntaPK=" + pruebaClaveAreaConocimientoPreguntaPK + " ]";
    }

}
