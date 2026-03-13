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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "jornada_aula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JornadaAula.findAll", query = "SELECT j FROM JornadaAula j"),
    @NamedQuery(name = "JornadaAula.findByIdJornada", query = "SELECT j FROM JornadaAula j WHERE j.jornadaAulaPK.idJornada = :idJornada"),
    @NamedQuery(name = "JornadaAula.findByIdAula", query = "SELECT j FROM JornadaAula j WHERE j.jornadaAulaPK.idAula = :idAula"),
    @NamedQuery(name = "JornadaAula.findByObservaciones", query = "SELECT j FROM JornadaAula j WHERE j.observaciones = :observaciones")})
public class JornadaAula implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JornadaAulaPK jornadaAulaPK;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Jornada jornada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornadaAula", fetch = FetchType.LAZY)
    private List<PruebaJornadaAulaAspiranteOpcion> pruebaJornadaAulaAspiranteOpcionList;

    public JornadaAula() {
    }

    public JornadaAula(JornadaAulaPK jornadaAulaPK) {
        this.jornadaAulaPK = jornadaAulaPK;
    }

    public JornadaAula(long idJornada, String idAula) {
        this.jornadaAulaPK = new JornadaAulaPK(idJornada, idAula);
    }

    public JornadaAulaPK getJornadaAulaPK() {
        return jornadaAulaPK;
    }

    public void setJornadaAulaPK(JornadaAulaPK jornadaAulaPK) {
        this.jornadaAulaPK = jornadaAulaPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    @XmlTransient
    public List<PruebaJornadaAulaAspiranteOpcion> getPruebaJornadaAulaAspiranteOpcionList() {
        return pruebaJornadaAulaAspiranteOpcionList;
    }

    public void setPruebaJornadaAulaAspiranteOpcionList(List<PruebaJornadaAulaAspiranteOpcion> pruebaJornadaAulaAspiranteOpcionList) {
        this.pruebaJornadaAulaAspiranteOpcionList = pruebaJornadaAulaAspiranteOpcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jornadaAulaPK != null ? jornadaAulaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JornadaAula)) {
            return false;
        }
        JornadaAula other = (JornadaAula) object;
        if ((this.jornadaAulaPK == null && other.jornadaAulaPK != null) || (this.jornadaAulaPK != null && !this.jornadaAulaPK.equals(other.jornadaAulaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula[ jornadaAulaPK=" + jornadaAulaPK + " ]";
    }

}
