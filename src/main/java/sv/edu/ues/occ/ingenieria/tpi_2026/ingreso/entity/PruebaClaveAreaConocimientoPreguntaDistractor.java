/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "prueba_clave_area_conocimiento_pregunta_distractor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findAll", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findByIdPruebaClave", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p WHERE p.pruebaClaveAreaConocimientoPreguntaDistractorPK.idPruebaClave = :idPruebaClave"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findByIdAreaConocimiento", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p WHERE p.pruebaClaveAreaConocimientoPreguntaDistractorPK.idAreaConocimiento = :idAreaConocimiento"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findByIdPregunta", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p WHERE p.pruebaClaveAreaConocimientoPreguntaDistractorPK.idPregunta = :idPregunta"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findByIdDistractor", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p WHERE p.pruebaClaveAreaConocimientoPreguntaDistractorPK.idDistractor = :idDistractor"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findByFechaCreacion", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PruebaClaveAreaConocimientoPreguntaDistractor.findByObservaciones", query = "SELECT p FROM PruebaClaveAreaConocimientoPreguntaDistractor p WHERE p.observaciones = :observaciones")})
public class PruebaClaveAreaConocimientoPreguntaDistractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaClaveAreaConocimientoPreguntaDistractorPK pruebaClaveAreaConocimientoPreguntaDistractorPK;
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_distractor", referencedColumnName = "id_distractor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Distractor distractor;
    @JoinColumns({
        @JoinColumn(name = "id_prueba_clave", referencedColumnName = "id_prueba_clave", insertable = false, updatable = false),
        @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento", insertable = false, updatable = false),
        @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPregunta;

    public PruebaClaveAreaConocimientoPreguntaDistractor() {
    }

    public PruebaClaveAreaConocimientoPreguntaDistractor(PruebaClaveAreaConocimientoPreguntaDistractorPK pruebaClaveAreaConocimientoPreguntaDistractorPK) {
        this.pruebaClaveAreaConocimientoPreguntaDistractorPK = pruebaClaveAreaConocimientoPreguntaDistractorPK;
    }

    public PruebaClaveAreaConocimientoPreguntaDistractor(long idPruebaClave, int idAreaConocimiento, long idPregunta, long idDistractor) {
        this.pruebaClaveAreaConocimientoPreguntaDistractorPK = new PruebaClaveAreaConocimientoPreguntaDistractorPK(idPruebaClave, idAreaConocimiento, idPregunta, idDistractor);
    }

    public PruebaClaveAreaConocimientoPreguntaDistractorPK getPruebaClaveAreaConocimientoPreguntaDistractorPK() {
        return pruebaClaveAreaConocimientoPreguntaDistractorPK;
    }

    public PruebaClaveAreaConocimientoPreguntaDistractor(PruebaClaveAreaConocimientoPreguntaDistractorPK pruebaClaveAreaConocimientoPreguntaDistractorPK, Date fechaCreacion, String observaciones) {
        this.pruebaClaveAreaConocimientoPreguntaDistractorPK = pruebaClaveAreaConocimientoPreguntaDistractorPK;
        this.fechaCreacion = fechaCreacion;
        this.observaciones = observaciones;
    }

    public void setPruebaClaveAreaConocimientoPreguntaDistractorPK(PruebaClaveAreaConocimientoPreguntaDistractorPK pruebaClaveAreaConocimientoPreguntaDistractorPK) {
        this.pruebaClaveAreaConocimientoPreguntaDistractorPK = pruebaClaveAreaConocimientoPreguntaDistractorPK;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Distractor getDistractor() {
        return distractor;
    }

    public void setDistractor(Distractor distractor) {
        this.distractor = distractor;
    }

    public PruebaClaveAreaConocimientoPregunta getPruebaClaveAreaConocimientoPregunta() {
        return pruebaClaveAreaConocimientoPregunta;
    }

    public void setPruebaClaveAreaConocimientoPregunta(PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPregunta) {
        this.pruebaClaveAreaConocimientoPregunta = pruebaClaveAreaConocimientoPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaClaveAreaConocimientoPreguntaDistractorPK != null ? pruebaClaveAreaConocimientoPreguntaDistractorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaClaveAreaConocimientoPreguntaDistractor)) {
            return false;
        }
        PruebaClaveAreaConocimientoPreguntaDistractor other = (PruebaClaveAreaConocimientoPreguntaDistractor) object;
        if ((this.pruebaClaveAreaConocimientoPreguntaDistractorPK == null && other.pruebaClaveAreaConocimientoPreguntaDistractorPK != null) || (this.pruebaClaveAreaConocimientoPreguntaDistractorPK != null && !this.pruebaClaveAreaConocimientoPreguntaDistractorPK.equals(other.pruebaClaveAreaConocimientoPreguntaDistractorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor[ pruebaClaveAreaConocimientoPreguntaDistractorPK=" + pruebaClaveAreaConocimientoPreguntaDistractorPK + " ]";
    }

}
