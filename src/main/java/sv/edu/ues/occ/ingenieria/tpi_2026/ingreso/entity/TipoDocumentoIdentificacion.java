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
@Table(name = "tipo_documento_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumentoIdentificacion.findAll", query = "SELECT t FROM TipoDocumentoIdentificacion t"),
    @NamedQuery(name = "TipoDocumentoIdentificacion.findByIdTipoDocumentoIdentificacion", query = "SELECT t FROM TipoDocumentoIdentificacion t WHERE t.idTipoDocumentoIdentificacion = :idTipoDocumentoIdentificacion"),
    @NamedQuery(name = "TipoDocumentoIdentificacion.findByNombre", query = "SELECT t FROM TipoDocumentoIdentificacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoDocumentoIdentificacion.findByObservaciones", query = "SELECT t FROM TipoDocumentoIdentificacion t WHERE t.observaciones = :observaciones")})
public class TipoDocumentoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento_identificacion")
    private Integer idTipoDocumentoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumentoIdentificacion")
    private List<DocumentoIdentificacion> documentoIdentificacionList;

    public TipoDocumentoIdentificacion() {
    }

    public TipoDocumentoIdentificacion(Integer idTipoDocumentoIdentificacion) {
        this.idTipoDocumentoIdentificacion = idTipoDocumentoIdentificacion;
    }

    public TipoDocumentoIdentificacion(Integer idTipoDocumentoIdentificacion, String nombre) {
        this.idTipoDocumentoIdentificacion = idTipoDocumentoIdentificacion;
        this.nombre = nombre;
    }

    public Integer getIdTipoDocumentoIdentificacion() {
        return idTipoDocumentoIdentificacion;
    }

    public void setIdTipoDocumentoIdentificacion(Integer idTipoDocumentoIdentificacion) {
        this.idTipoDocumentoIdentificacion = idTipoDocumentoIdentificacion;
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
    public List<DocumentoIdentificacion> getDocumentoIdentificacionList() {
        return documentoIdentificacionList;
    }

    public void setDocumentoIdentificacionList(List<DocumentoIdentificacion> documentoIdentificacionList) {
        this.documentoIdentificacionList = documentoIdentificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumentoIdentificacion != null ? idTipoDocumentoIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumentoIdentificacion)) {
            return false;
        }
        TipoDocumentoIdentificacion other = (TipoDocumentoIdentificacion) object;
        if ((this.idTipoDocumentoIdentificacion == null && other.idTipoDocumentoIdentificacion != null) || (this.idTipoDocumentoIdentificacion != null && !this.idTipoDocumentoIdentificacion.equals(other.idTipoDocumentoIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoDocumentoIdentificacion[ idTipoDocumentoIdentificacion=" + idTipoDocumentoIdentificacion + " ]";
    }

}
