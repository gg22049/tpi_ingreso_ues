/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "pregunta_distractor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntaDistractor.findAll", query = "SELECT p FROM PreguntaDistractor p"),
    @NamedQuery(name = "PreguntaDistractor.findByIdPregunta", query = "SELECT p FROM PreguntaDistractor p WHERE p.preguntaDistractorPK.idPregunta = :idPregunta"),
    @NamedQuery(name = "PreguntaDistractor.findByIdDistractor", query = "SELECT p FROM PreguntaDistractor p WHERE p.preguntaDistractorPK.idDistractor = :idDistractor"),
    @NamedQuery(name = "PreguntaDistractor.findByCorrecto", query = "SELECT p FROM PreguntaDistractor p WHERE p.correcto = :correcto"),
    @NamedQuery(name = "PreguntaDistractor.findByObservaciones", query = "SELECT p FROM PreguntaDistractor p WHERE p.observaciones = :observaciones")})
public class PreguntaDistractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreguntaDistractorPK preguntaDistractorPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correcto")
    private boolean correcto;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_distractor", referencedColumnName = "id_distractor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Distractor distractor;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta pregunta;

    public PreguntaDistractor() {
    }

    public PreguntaDistractor(PreguntaDistractorPK preguntaDistractorPK) {
        this.preguntaDistractorPK = preguntaDistractorPK;
    }

    public PreguntaDistractor(PreguntaDistractorPK preguntaDistractorPK, boolean correcto) {
        this.preguntaDistractorPK = preguntaDistractorPK;
        this.correcto = correcto;
    }

    public PreguntaDistractor(long idPregunta, long idDistractor) {
        this.preguntaDistractorPK = new PreguntaDistractorPK(idPregunta, idDistractor);
    }

    public PreguntaDistractor(PreguntaDistractorPK preguntaDistractorPK, boolean correcto, String observaciones) {
        this.preguntaDistractorPK = preguntaDistractorPK;
        this.correcto = correcto;
        this.observaciones = observaciones;
    }

    public PreguntaDistractorPK getPreguntaDistractorPK() {
        return preguntaDistractorPK;
    }

    public void setPreguntaDistractorPK(PreguntaDistractorPK preguntaDistractorPK) {
        this.preguntaDistractorPK = preguntaDistractorPK;
    }

    public boolean getCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
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

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaDistractorPK != null ? preguntaDistractorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaDistractor)) {
            return false;
        }
        PreguntaDistractor other = (PreguntaDistractor) object;
        if ((this.preguntaDistractorPK == null && other.preguntaDistractorPK != null) || (this.preguntaDistractorPK != null && !this.preguntaDistractorPK.equals(other.preguntaDistractorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor[ preguntaDistractorPK=" + preguntaDistractorPK + " ]";
    }

}
