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
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "aspirante_opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AspiranteOpcion.findAll", query = "SELECT a FROM AspiranteOpcion a"),
    @NamedQuery(name = "AspiranteOpcion.findByIdAspiranteOpcion", query = "SELECT a FROM AspiranteOpcion a WHERE a.idAspiranteOpcion = :idAspiranteOpcion"),
    @NamedQuery(name = "AspiranteOpcion.findByIdOpcion", query = "SELECT a FROM AspiranteOpcion a WHERE a.idOpcion = :idOpcion"),
    @NamedQuery(name = "AspiranteOpcion.findByFechaCreacion", query = "SELECT a FROM AspiranteOpcion a WHERE a.fechaCreacion = :fechaCreacion")})
public class AspiranteOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aspirante_opcion")
    private Long idAspiranteOpcion;
    @Basic(optional = false)
    @NotBlank
    @Size(min = 1, max = 124)
    @Column(name = "id_opcion")
    private String idOpcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspiranteOpcion", fetch = FetchType.LAZY)
    private List<PruebaJornadaAulaAspiranteOpcion> pruebaJornadaAulaAspiranteOpcionList;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aspirante idAspirante;

    public AspiranteOpcion() {
    }

    public AspiranteOpcion(Long idAspiranteOpcion) {
        this.idAspiranteOpcion = idAspiranteOpcion;
    }

    public AspiranteOpcion(Long idAspiranteOpcion, String idOpcion, Date fechaCreacion) {
        this.idAspiranteOpcion = idAspiranteOpcion;
        this.idOpcion = idOpcion;
        this.fechaCreacion = fechaCreacion;
    }

    public AspiranteOpcion(Long idAspiranteOpcion, String idOpcion, Date fechaCreacion, Aspirante idAspirante) {
        this.idAspiranteOpcion = idAspiranteOpcion;
        this.idOpcion = idOpcion;
        this.fechaCreacion = fechaCreacion;
        this.idAspirante = idAspirante;
    }

    public Long getIdAspiranteOpcion() {
        return idAspiranteOpcion;
    }

    public void setIdAspiranteOpcion(Long idAspiranteOpcion) {
        this.idAspiranteOpcion = idAspiranteOpcion;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlTransient
    public List<PruebaJornadaAulaAspiranteOpcion> getPruebaJornadaAulaAspiranteOpcionList() {
        return pruebaJornadaAulaAspiranteOpcionList;
    }

    public void setPruebaJornadaAulaAspiranteOpcionList(List<PruebaJornadaAulaAspiranteOpcion> pruebaJornadaAulaAspiranteOpcionList) {
        this.pruebaJornadaAulaAspiranteOpcionList = pruebaJornadaAulaAspiranteOpcionList;
    }

    public Aspirante getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirante idAspirante) {
        this.idAspirante = idAspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAspiranteOpcion != null ? idAspiranteOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AspiranteOpcion)) {
            return false;
        }
        AspiranteOpcion other = (AspiranteOpcion) object;
        if ((this.idAspiranteOpcion == null && other.idAspiranteOpcion != null) || (this.idAspiranteOpcion != null && !this.idAspiranteOpcion.equals(other.idAspiranteOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion[ idAspiranteOpcion=" + idAspiranteOpcion + " ]";
    }

}
