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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "pregunta_area_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntaAreaConocimiento.findAll", query = "SELECT p FROM PreguntaAreaConocimiento p"),
    @NamedQuery(name = "PreguntaAreaConocimiento.findByIdPregunta", query = "SELECT p FROM PreguntaAreaConocimiento p WHERE p.preguntaAreaConocimientoPK.idPregunta = :idPregunta"),
    @NamedQuery(name = "PreguntaAreaConocimiento.findByIdAreaConocimiento", query = "SELECT p FROM PreguntaAreaConocimiento p WHERE p.preguntaAreaConocimientoPK.idAreaConocimiento = :idAreaConocimiento"),
    @NamedQuery(name = "PreguntaAreaConocimiento.findByObservaciones", query = "SELECT p FROM PreguntaAreaConocimiento p WHERE p.observaciones = :observaciones")})
public class PreguntaAreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreguntaAreaConocimientoPK preguntaAreaConocimientoPK;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreaConocimiento areaConocimiento;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta pregunta;

    public PreguntaAreaConocimiento() {
    }

    public PreguntaAreaConocimiento(PreguntaAreaConocimientoPK preguntaAreaConocimientoPK) {
        this.preguntaAreaConocimientoPK = preguntaAreaConocimientoPK;
    }

    public PreguntaAreaConocimiento(long idPregunta, int idAreaConocimiento) {
        this.preguntaAreaConocimientoPK = new PreguntaAreaConocimientoPK(idPregunta, idAreaConocimiento);
    }

    public PreguntaAreaConocimiento(PreguntaAreaConocimientoPK preguntaAreaConocimientoPK, String observaciones) {
        this.preguntaAreaConocimientoPK = preguntaAreaConocimientoPK;
        this.observaciones = observaciones;
    }

    public PreguntaAreaConocimientoPK getPreguntaAreaConocimientoPK() {
        return preguntaAreaConocimientoPK;
    }

    public void setPreguntaAreaConocimientoPK(PreguntaAreaConocimientoPK preguntaAreaConocimientoPK) {
        this.preguntaAreaConocimientoPK = preguntaAreaConocimientoPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public AreaConocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaAreaConocimientoPK != null ? preguntaAreaConocimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaAreaConocimiento)) {
            return false;
        }
        PreguntaAreaConocimiento other = (PreguntaAreaConocimiento) object;
        if ((this.preguntaAreaConocimientoPK == null && other.preguntaAreaConocimientoPK != null) || (this.preguntaAreaConocimientoPK != null && !this.preguntaAreaConocimientoPK.equals(other.preguntaAreaConocimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento[ preguntaAreaConocimientoPK=" + preguntaAreaConocimientoPK + " ]";
    }

}
