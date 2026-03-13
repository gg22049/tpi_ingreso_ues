/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Embeddable
public class PreguntaDistractorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pregunta")
    private long idPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_distractor")
    private long idDistractor;

    public PreguntaDistractorPK() {
    }

    public PreguntaDistractorPK(long idPregunta, long idDistractor) {
        this.idPregunta = idPregunta;
        this.idDistractor = idDistractor;
    }

    public long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public long getIdDistractor() {
        return idDistractor;
    }

    public void setIdDistractor(long idDistractor) {
        this.idDistractor = idDistractor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPregunta;
        hash += (int) idDistractor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaDistractorPK)) {
            return false;
        }
        PreguntaDistractorPK other = (PreguntaDistractorPK) object;
        if (this.idPregunta != other.idPregunta) {
            return false;
        }
        if (this.idDistractor != other.idDistractor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractorPK[ idPregunta=" + idPregunta + ", idDistractor=" + idDistractor + " ]";
    }

}
