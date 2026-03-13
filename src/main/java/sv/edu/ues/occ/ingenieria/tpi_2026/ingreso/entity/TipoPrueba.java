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
@Table(name = "tipo_prueba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPrueba.findAll", query = "SELECT t FROM TipoPrueba t"),
    @NamedQuery(name = "TipoPrueba.findByIdTipoPrueba", query = "SELECT t FROM TipoPrueba t WHERE t.idTipoPrueba = :idTipoPrueba"),
    @NamedQuery(name = "TipoPrueba.findByValor", query = "SELECT t FROM TipoPrueba t WHERE t.valor = :valor"),
    @NamedQuery(name = "TipoPrueba.findByActivo", query = "SELECT t FROM TipoPrueba t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoPrueba.findByObservaciones", query = "SELECT t FROM TipoPrueba t WHERE t.observaciones = :observaciones")})
public class TipoPrueba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_prueba")
    private Integer idTipoPrueba;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPrueba", fetch = FetchType.LAZY)
    private List<Prueba> pruebaList;

    public TipoPrueba() {
    }

    public TipoPrueba(Integer idTipoPrueba) {
        this.idTipoPrueba = idTipoPrueba;
    }

    public TipoPrueba(Integer idTipoPrueba, String valor) {
        this.idTipoPrueba = idTipoPrueba;
        this.valor = valor;
    }

    public Integer getIdTipoPrueba() {
        return idTipoPrueba;
    }

    public void setIdTipoPrueba(Integer idTipoPrueba) {
        this.idTipoPrueba = idTipoPrueba;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Prueba> getPruebaList() {
        return pruebaList;
    }

    public void setPruebaList(List<Prueba> pruebaList) {
        this.pruebaList = pruebaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPrueba != null ? idTipoPrueba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPrueba)) {
            return false;
        }
        TipoPrueba other = (TipoPrueba) object;
        if ((this.idTipoPrueba == null && other.idTipoPrueba != null) || (this.idTipoPrueba != null && !this.idTipoPrueba.equals(other.idTipoPrueba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba[ idTipoPrueba=" + idTipoPrueba + " ]";
    }

}
