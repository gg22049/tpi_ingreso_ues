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
public class PreguntaAreaConocimientoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pregunta")
    private long idPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_area_conocimiento")
    private int idAreaConocimiento;

    public PreguntaAreaConocimientoPK() {
    }

    public PreguntaAreaConocimientoPK(long idPregunta, int idAreaConocimiento) {
        this.idPregunta = idPregunta;
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdAreaConocimiento() {
        return idAreaConocimiento;
    }

    public void setIdAreaConocimiento(int idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPregunta;
        hash += (int) idAreaConocimiento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaAreaConocimientoPK)) {
            return false;
        }
        PreguntaAreaConocimientoPK other = (PreguntaAreaConocimientoPK) object;
        if (this.idPregunta != other.idPregunta) {
            return false;
        }
        if (this.idAreaConocimiento != other.idAreaConocimiento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimientoPK[ idPregunta=" + idPregunta + ", idAreaConocimiento=" + idAreaConocimiento + " ]";
    }

}
