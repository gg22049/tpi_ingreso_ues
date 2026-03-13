/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Embeddable
public class JornadaAulaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_jornada")
    private long idJornada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 124)
    @Column(name = "id_aula")
    private String idAula;

    public JornadaAulaPK() {
    }

    public JornadaAulaPK(long idJornada, String idAula) {
        this.idJornada = idJornada;
        this.idAula = idAula;
    }

    public long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(long idJornada) {
        this.idJornada = idJornada;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idJornada;
        hash += (idAula != null ? idAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JornadaAulaPK)) {
            return false;
        }
        JornadaAulaPK other = (JornadaAulaPK) object;
        if (this.idJornada != other.idJornada) {
            return false;
        }
        if ((this.idAula == null && other.idAula != null) || (this.idAula != null && !this.idAula.equals(other.idAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK[ idJornada=" + idJornada + ", idAula=" + idAula + " ]";
    }

}
