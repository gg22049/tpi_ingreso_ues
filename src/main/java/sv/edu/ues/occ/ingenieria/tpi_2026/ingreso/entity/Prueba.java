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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "prueba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prueba.findAll", query = "SELECT p FROM Prueba p"),
    @NamedQuery(name = "Prueba.findByIdPrueba", query = "SELECT p FROM Prueba p WHERE p.idPrueba = :idPrueba"),
    @NamedQuery(name = "Prueba.findByNombre", query = "SELECT p FROM Prueba p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Prueba.findByIndicaciones", query = "SELECT p FROM Prueba p WHERE p.indicaciones = :indicaciones"),
    @NamedQuery(name = "Prueba.findByPuntajeMaximo", query = "SELECT p FROM Prueba p WHERE p.puntajeMaximo = :puntajeMaximo"),
    @NamedQuery(name = "Prueba.findByNotaAprobacion", query = "SELECT p FROM Prueba p WHERE p.notaAprobacion = :notaAprobacion"),
    @NamedQuery(name = "Prueba.findByDuracion", query = "SELECT p FROM Prueba p WHERE p.duracion = :duracion"),
    @NamedQuery(name = "Prueba.findByFechaCreacion", query = "SELECT p FROM Prueba p WHERE p.fechaCreacion = :fechaCreacion")})
public class Prueba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prueba")
    private Long idPrueba;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 124)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "indicaciones")
    private String indicaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje_maximo")
    private BigDecimal puntajeMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota_aprobacion")
    private BigDecimal notaAprobacion;
    @Column(name = "duracion")
    private Integer duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "id_tipo_prueba", referencedColumnName = "id_tipo_prueba")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPrueba idTipoPrueba;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prueba", fetch = FetchType.LAZY)
    private List<PruebaJornadaAulaAspiranteOpcion> pruebaJornadaAulaAspiranteOpcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrueba", fetch = FetchType.LAZY)
    private List<PruebaClave> pruebaClaveList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prueba", fetch = FetchType.LAZY)
    private List<PruebaJornada> pruebaJornadaList;

    public Prueba() {
    }

    public Prueba(Long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public Prueba(Long idPrueba, String nombre, BigDecimal puntajeMaximo, BigDecimal notaAprobacion, Date fechaCreacion) {
        this.idPrueba = idPrueba;
        this.nombre = nombre;
        this.puntajeMaximo = puntajeMaximo;
        this.notaAprobacion = notaAprobacion;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public BigDecimal getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(BigDecimal puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public BigDecimal getNotaAprobacion() {
        return notaAprobacion;
    }

    public void setNotaAprobacion(BigDecimal notaAprobacion) {
        this.notaAprobacion = notaAprobacion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoPrueba getIdTipoPrueba() {
        return idTipoPrueba;
    }

    public void setIdTipoPrueba(TipoPrueba idTipoPrueba) {
        this.idTipoPrueba = idTipoPrueba;
    }

    @XmlTransient
    public List<PruebaJornadaAulaAspiranteOpcion> getPruebaJornadaAulaAspiranteOpcionList() {
        return pruebaJornadaAulaAspiranteOpcionList;
    }

    public void setPruebaJornadaAulaAspiranteOpcionList(List<PruebaJornadaAulaAspiranteOpcion> pruebaJornadaAulaAspiranteOpcionList) {
        this.pruebaJornadaAulaAspiranteOpcionList = pruebaJornadaAulaAspiranteOpcionList;
    }

    @XmlTransient
    public List<PruebaClave> getPruebaClaveList() {
        return pruebaClaveList;
    }

    public void setPruebaClaveList(List<PruebaClave> pruebaClaveList) {
        this.pruebaClaveList = pruebaClaveList;
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
        hash += (idPrueba != null ? idPrueba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prueba)) {
            return false;
        }
        Prueba other = (Prueba) object;
        if ((this.idPrueba == null && other.idPrueba != null) || (this.idPrueba != null && !this.idPrueba.equals(other.idPrueba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba[ idPrueba=" + idPrueba + " ]";
    }

}
