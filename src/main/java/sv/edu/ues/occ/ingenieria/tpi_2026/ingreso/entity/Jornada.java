/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "jornada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jornada.findAll", query = "SELECT j FROM Jornada j"),
    @NamedQuery(name = "Jornada.findByIdJornada", query = "SELECT j FROM Jornada j WHERE j.idJornada = :idJornada"),
    @NamedQuery(name = "Jornada.findByNombre", query = "SELECT j FROM Jornada j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "Jornada.findByFechaInicio", query = "SELECT j FROM Jornada j WHERE j.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Jornada.findByFechaFin", query = "SELECT j FROM Jornada j WHERE j.fechaFin = :fechaFin"),
    @NamedQuery(name = "Jornada.findByObservaciones", query = "SELECT j FROM Jornada j WHERE j.observaciones = :observaciones")})
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jornada")
    private Long idJornada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 124)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornada", fetch = FetchType.LAZY)
    private List<JornadaAula> jornadaAulaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jornada", fetch = FetchType.LAZY)
    private List<PruebaJornada> pruebaJornadaList;

    public Jornada() {
    }

    public Jornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public Jornada(Long idJornada, String nombre, Date fechaInicio, Date fechaFin) {
        this.idJornada = idJornada;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<JornadaAula> getJornadaAulaList() {
        return jornadaAulaList;
    }

    public void setJornadaAulaList(List<JornadaAula> jornadaAulaList) {
        this.jornadaAulaList = jornadaAulaList;
    }

    @XmlTransient
    public List<PruebaJornada> getPruebaJornadaList() {
        return pruebaJornadaList;
    }

    public void setPruebaJornadaList(List<PruebaJornada> pruebaJornadaList) {
        this.pruebaJornadaList = pruebaJornadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jornada)) {
            return false;
        }
        Jornada other = (Jornada) object;
        if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada[ idJornada=" + idJornada + " ]";
    }

}
