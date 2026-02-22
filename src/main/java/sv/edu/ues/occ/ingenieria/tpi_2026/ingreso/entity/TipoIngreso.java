/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "tipo_ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIngreso.findAll", query = "SELECT t FROM TipoIngreso t"),
    @NamedQuery(name = "TipoIngreso.findByIdTipoIngreso", query = "SELECT t FROM TipoIngreso t WHERE t.idTipoIngreso = :idTipoIngreso"),
    @NamedQuery(name = "TipoIngreso.findByNombre", query = "SELECT t FROM TipoIngreso t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoIngreso.findByObservaciones", query = "SELECT t FROM TipoIngreso t WHERE t.observaciones = :observaciones")})
public class TipoIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_ingreso")
    private Integer idTipoIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoIngreso")
    private List<Aspirante> aspiranteList;

    public TipoIngreso() {
    }

    public TipoIngreso(Integer idTipoIngreso) {
        this.idTipoIngreso = idTipoIngreso;
    }

    public TipoIngreso(Integer idTipoIngreso, String nombre) {
        this.idTipoIngreso = idTipoIngreso;
        this.nombre = nombre;
    }

    public Integer getIdTipoIngreso() {
        return idTipoIngreso;
    }

    public void setIdTipoIngreso(Integer idTipoIngreso) {
        this.idTipoIngreso = idTipoIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Aspirante> getAspiranteList() {
        return aspiranteList;
    }

    public void setAspiranteList(List<Aspirante> aspiranteList) {
        this.aspiranteList = aspiranteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoIngreso != null ? idTipoIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoIngreso)) {
            return false;
        }
        TipoIngreso other = (TipoIngreso) object;
        if ((this.idTipoIngreso == null && other.idTipoIngreso != null) || (this.idTipoIngreso != null && !this.idTipoIngreso.equals(other.idTipoIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIngreso[ idTipoIngreso=" + idTipoIngreso + " ]";
    }

}
