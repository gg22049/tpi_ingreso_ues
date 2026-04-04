/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "prueba_jornada_aula_aspirante_opcion_examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findAll", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByIdPrueba", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.pruebaJornadaAulaAspiranteOpcionExamenPK.idPrueba = :idPrueba"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByIdJornada", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.pruebaJornadaAulaAspiranteOpcionExamenPK.idJornada = :idJornada"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByIdAula", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.pruebaJornadaAulaAspiranteOpcionExamenPK.idAula = :idAula"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByIdAspiranteOpcion", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.pruebaJornadaAulaAspiranteOpcionExamenPK.idAspiranteOpcion = :idAspiranteOpcion"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByResultado", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.resultado = :resultado"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByFechaResultado", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.fechaResultado = :fechaResultado"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByImagenUrl", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.imagenUrl = :imagenUrl"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcionExamen.findByObservaciones", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcionExamen p WHERE p.observaciones = :observaciones")})
public class PruebaJornadaAulaAspiranteOpcionExamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaJornadaAulaAspiranteOpcionExamenPK pruebaJornadaAulaAspiranteOpcionExamenPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resultado")
    private BigDecimal resultado;
    @Column(name = "fecha_resultado")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResultado;
    @Size(max = 64)
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumns({
        @JoinColumn(name = "id_prueba", referencedColumnName = "id_prueba", insertable = false, updatable = false),
        @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada", insertable = false, updatable = false),
        @JoinColumn(name = "id_aula", referencedColumnName = "id_aula", insertable = false, updatable = false),
        @JoinColumn(name = "id_aspirante_opcion", referencedColumnName = "id_aspirante_opcion", insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcion;

    public PruebaJornadaAulaAspiranteOpcionExamen() {
    }

    public PruebaJornadaAulaAspiranteOpcionExamen(PruebaJornadaAulaAspiranteOpcionExamenPK pruebaJornadaAulaAspiranteOpcionExamenPK) {
        this.pruebaJornadaAulaAspiranteOpcionExamenPK = pruebaJornadaAulaAspiranteOpcionExamenPK;
    }

    public PruebaJornadaAulaAspiranteOpcionExamen(PruebaJornadaAulaAspiranteOpcionExamenPK pruebaJornadaAulaAspiranteOpcionExamenPK, BigDecimal resultado) {
        this.pruebaJornadaAulaAspiranteOpcionExamenPK = pruebaJornadaAulaAspiranteOpcionExamenPK;
        this.resultado = resultado;
    }

    public PruebaJornadaAulaAspiranteOpcionExamen(long idPrueba, long idJornada, String idAula, long idAspiranteOpcion) {
        this.pruebaJornadaAulaAspiranteOpcionExamenPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion);
    }

    public PruebaJornadaAulaAspiranteOpcionExamen(PruebaJornadaAulaAspiranteOpcionExamenPK pruebaJornadaAulaAspiranteOpcionExamenPK, BigDecimal resultado, Date fechaResultado, String imagenUrl, String observaciones) {
        this.pruebaJornadaAulaAspiranteOpcionExamenPK = pruebaJornadaAulaAspiranteOpcionExamenPK;
        this.resultado = resultado;
        this.fechaResultado = fechaResultado;
        this.imagenUrl = imagenUrl;
        this.observaciones = observaciones;
    }

    public PruebaJornadaAulaAspiranteOpcionExamenPK getPruebaJornadaAulaAspiranteOpcionExamenPK() {
        return pruebaJornadaAulaAspiranteOpcionExamenPK;
    }

    public void setPruebaJornadaAulaAspiranteOpcionExamenPK(PruebaJornadaAulaAspiranteOpcionExamenPK pruebaJornadaAulaAspiranteOpcionExamenPK) {
        this.pruebaJornadaAulaAspiranteOpcionExamenPK = pruebaJornadaAulaAspiranteOpcionExamenPK;
    }

    public BigDecimal getResultado() {
        return resultado;
    }

    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }

    public Date getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(Date fechaResultado) {
        this.fechaResultado = fechaResultado;
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

    public PruebaJornadaAulaAspiranteOpcion getPruebaJornadaAulaAspiranteOpcion() {
        return pruebaJornadaAulaAspiranteOpcion;
    }

    public void setPruebaJornadaAulaAspiranteOpcion(PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcion) {
        this.pruebaJornadaAulaAspiranteOpcion = pruebaJornadaAulaAspiranteOpcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaJornadaAulaAspiranteOpcionExamenPK != null ? pruebaJornadaAulaAspiranteOpcionExamenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaJornadaAulaAspiranteOpcionExamen)) {
            return false;
        }
        PruebaJornadaAulaAspiranteOpcionExamen other = (PruebaJornadaAulaAspiranteOpcionExamen) object;
        if ((this.pruebaJornadaAulaAspiranteOpcionExamenPK == null && other.pruebaJornadaAulaAspiranteOpcionExamenPK != null) || (this.pruebaJornadaAulaAspiranteOpcionExamenPK != null && !this.pruebaJornadaAulaAspiranteOpcionExamenPK.equals(other.pruebaJornadaAulaAspiranteOpcionExamenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen[ pruebaJornadaAulaAspiranteOpcionExamenPK=" + pruebaJornadaAulaAspiranteOpcionExamenPK + " ]";
    }

}
