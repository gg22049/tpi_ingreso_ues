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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "prueba_jornada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaJornada.findAll", query = "SELECT p FROM PruebaJornada p"),
    @NamedQuery(name = "PruebaJornada.findByIdPrueba", query = "SELECT p FROM PruebaJornada p WHERE p.pruebaJornadaPK.idPrueba = :idPrueba"),
    @NamedQuery(name = "PruebaJornada.findByIdJornada", query = "SELECT p FROM PruebaJornada p WHERE p.pruebaJornadaPK.idJornada = :idJornada"),
    @NamedQuery(name = "PruebaJornada.findByFechaCreacion", query = "SELECT p FROM PruebaJornada p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PruebaJornada.findByObservaciones", query = "SELECT p FROM PruebaJornada p WHERE p.observaciones = :observaciones")})
public class PruebaJornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaJornadaPK pruebaJornadaPK;
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Jornada jornada;
    @JoinColumn(name = "id_prueba", referencedColumnName = "id_prueba", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Prueba prueba;

    public PruebaJornada() {
    }

    public PruebaJornada(PruebaJornadaPK pruebaJornadaPK) {
        this.pruebaJornadaPK = pruebaJornadaPK;
    }

    public PruebaJornada(long idPrueba, long idJornada) {
        this.pruebaJornadaPK = new PruebaJornadaPK(idPrueba, idJornada);
    }

    public PruebaJornada(PruebaJornadaPK pruebaJornadaPK, Date fechaCreacion, String observaciones) {
        this.pruebaJornadaPK = pruebaJornadaPK;
        this.fechaCreacion = fechaCreacion;
        this.observaciones = observaciones;
    }

    public PruebaJornadaPK getPruebaJornadaPK() {
        return pruebaJornadaPK;
    }

    public void setPruebaJornadaPK(PruebaJornadaPK pruebaJornadaPK) {
        this.pruebaJornadaPK = pruebaJornadaPK;
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

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaJornadaPK != null ? pruebaJornadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaJornada)) {
            return false;
        }
        PruebaJornada other = (PruebaJornada) object;
        if ((this.pruebaJornadaPK == null && other.pruebaJornadaPK != null) || (this.pruebaJornadaPK != null && !this.pruebaJornadaPK.equals(other.pruebaJornadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada[ pruebaJornadaPK=" + pruebaJornadaPK + " ]";
    }

}
