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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "aspirante_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AspiranteIdentificacion.findAll", query = "SELECT a FROM AspiranteIdentificacion a"),
    @NamedQuery(name = "AspiranteIdentificacion.findByIdAspirante", query = "SELECT a FROM AspiranteIdentificacion a WHERE a.aspiranteIdentificacionPK.idAspirante = :idAspirante"),
    @NamedQuery(name = "AspiranteIdentificacion.findByIdTipoIdentificacion", query = "SELECT a FROM AspiranteIdentificacion a WHERE a.aspiranteIdentificacionPK.idTipoIdentificacion = :idTipoIdentificacion"),
    @NamedQuery(name = "AspiranteIdentificacion.findByValor", query = "SELECT a FROM AspiranteIdentificacion a WHERE a.valor = :valor"),
    @NamedQuery(name = "AspiranteIdentificacion.findByImagenUrl", query = "SELECT a FROM AspiranteIdentificacion a WHERE a.imagenUrl = :imagenUrl"),
    @NamedQuery(name = "AspiranteIdentificacion.findByObservaciones", query = "SELECT a FROM AspiranteIdentificacion a WHERE a.observaciones = :observaciones")})
public class AspiranteIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AspiranteIdentificacionPK aspiranteIdentificacionPK;
    @NotNull
    @Size(max = 32)
    @Column(name = "valor")
    private String valor;
    @Size(max = 64)
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aspirante aspirante;
    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoIdentificacion tipoIdentificacion;

    public AspiranteIdentificacion() {
    }

    public AspiranteIdentificacion(AspiranteIdentificacionPK aspiranteIdentificacionPK, String valor, String imagenUrl, String observaciones) {
        this.aspiranteIdentificacionPK = aspiranteIdentificacionPK;
        this.valor = valor;
        this.imagenUrl = imagenUrl;
        this.observaciones = observaciones;
    }

    public AspiranteIdentificacion(AspiranteIdentificacionPK aspiranteIdentificacionPK) {
        this.aspiranteIdentificacionPK = aspiranteIdentificacionPK;
    }

    public AspiranteIdentificacion(int idAspirante, int idTipoIdentificacion) {
        this.aspiranteIdentificacionPK = new AspiranteIdentificacionPK(idAspirante, idTipoIdentificacion);
    }

    public AspiranteIdentificacionPK getAspiranteIdentificacionPK() {
        return aspiranteIdentificacionPK;
    }

    public void setAspiranteIdentificacionPK(AspiranteIdentificacionPK aspiranteIdentificacionPK) {
        this.aspiranteIdentificacionPK = aspiranteIdentificacionPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aspiranteIdentificacionPK != null ? aspiranteIdentificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AspiranteIdentificacion)) {
            return false;
        }
        AspiranteIdentificacion other = (AspiranteIdentificacion) object;
        if ((this.aspiranteIdentificacionPK == null && other.aspiranteIdentificacionPK != null) || (this.aspiranteIdentificacionPK != null && !this.aspiranteIdentificacionPK.equals(other.aspiranteIdentificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacion[ aspiranteIdentificacionPK=" + aspiranteIdentificacionPK + " ]";
    }

}
