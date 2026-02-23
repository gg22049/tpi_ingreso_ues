/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "documento_identificacion")
@NamedQueries({
    @NamedQuery(name = "DocumentoIdentificacion.findAll", query = "SELECT d FROM DocumentoIdentificacion d"),
    @NamedQuery(name = "DocumentoIdentificacion.findByIdDocumentoIdentificacion", query = "SELECT d FROM DocumentoIdentificacion d WHERE d.idDocumentoIdentificacion = :idDocumentoIdentificacion"),
    @NamedQuery(name = "DocumentoIdentificacion.findByVigente", query = "SELECT d FROM DocumentoIdentificacion d WHERE d.vigente = :vigente"),
    @NamedQuery(name = "DocumentoIdentificacion.findByNumero", query = "SELECT d FROM DocumentoIdentificacion d WHERE d.numero = :numero"),
    @NamedQuery(name = "DocumentoIdentificacion.findByObservaciones", query = "SELECT d FROM DocumentoIdentificacion d WHERE d.observaciones = :observaciones")})
public class DocumentoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento_identificacion")
    private Long idDocumentoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigente")
    private boolean vigente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante")
    @ManyToOne(optional = false)
    private Aspirante idAspirante;
    @JoinColumn(name = "id_tipo_documento_identificacion", referencedColumnName = "id_tipo_documento_identificacion")
    @ManyToOne(optional = false)
    private TipoDocumentoIdentificacion idTipoDocumentoIdentificacion;

    public DocumentoIdentificacion() {
    }

    public DocumentoIdentificacion(Long idDocumentoIdentificacion) {
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
    }

    public DocumentoIdentificacion(Long idDocumentoIdentificacion, boolean vigente, int numero) {
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
        this.vigente = vigente;
        this.numero = numero;
    }

    public Long getIdDocumentoIdentificacion() {
        return idDocumentoIdentificacion;
    }

    public void setIdDocumentoIdentificacion(Long idDocumentoIdentificacion) {
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
    }

    public boolean getVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Aspirante getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirante idAspirante) {
        this.idAspirante = idAspirante;
    }

    public TipoDocumentoIdentificacion getIdTipoDocumentoIdentificacion() {
        return idTipoDocumentoIdentificacion;
    }

    public void setIdTipoDocumentoIdentificacion(TipoDocumentoIdentificacion idTipoDocumentoIdentificacion) {
        this.idTipoDocumentoIdentificacion = idTipoDocumentoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentoIdentificacion != null ? idDocumentoIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoIdentificacion)) {
            return false;
        }
        DocumentoIdentificacion other = (DocumentoIdentificacion) object;
        if ((this.idDocumentoIdentificacion == null && other.idDocumentoIdentificacion != null) || (this.idDocumentoIdentificacion != null && !this.idDocumentoIdentificacion.equals(other.idDocumentoIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DocumentoIdentificacion[ idDocumentoIdentificacion=" + idDocumentoIdentificacion + " ]";
    }

}
