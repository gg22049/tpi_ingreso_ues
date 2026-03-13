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
@Table(name = "prueba_clave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaClave.findAll", query = "SELECT p FROM PruebaClave p"),
    @NamedQuery(name = "PruebaClave.findByIdPruebaClave", query = "SELECT p FROM PruebaClave p WHERE p.idPruebaClave = :idPruebaClave"),
    @NamedQuery(name = "PruebaClave.findByNombre", query = "SELECT p FROM PruebaClave p WHERE p.nombre = :nombre")})
public class PruebaClave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prueba_clave")
    private Long idPruebaClave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pruebaClave", fetch = FetchType.LAZY)
    private List<PruebaClaveAreaConocimiento> pruebaClaveAreaConocimientoList;
    @JoinColumn(name = "id_prueba", referencedColumnName = "id_prueba")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Prueba idPrueba;

    public PruebaClave() {
    }

    public PruebaClave(Long idPruebaClave) {
        this.idPruebaClave = idPruebaClave;
    }

    public PruebaClave(Long idPruebaClave, String nombre) {
        this.idPruebaClave = idPruebaClave;
        this.nombre = nombre;
    }

    public Long getIdPruebaClave() {
        return idPruebaClave;
    }

    public void setIdPruebaClave(Long idPruebaClave) {
        this.idPruebaClave = idPruebaClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<PruebaClaveAreaConocimiento> getPruebaClaveAreaConocimientoList() {
        return pruebaClaveAreaConocimientoList;
    }

    public void setPruebaClaveAreaConocimientoList(List<PruebaClaveAreaConocimiento> pruebaClaveAreaConocimientoList) {
        this.pruebaClaveAreaConocimientoList = pruebaClaveAreaConocimientoList;
    }

    public Prueba getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Prueba idPrueba) {
        this.idPrueba = idPrueba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPruebaClave != null ? idPruebaClave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaClave)) {
            return false;
        }
        PruebaClave other = (PruebaClave) object;
        if ((this.idPruebaClave == null && other.idPruebaClave != null) || (this.idPruebaClave != null && !this.idPruebaClave.equals(other.idPruebaClave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave[ idPruebaClave=" + idPruebaClave + " ]";
    }

}
