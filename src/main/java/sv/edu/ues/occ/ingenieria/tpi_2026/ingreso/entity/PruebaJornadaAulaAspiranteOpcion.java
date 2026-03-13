/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "prueba_jornada_aula_aspirante_opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findAll", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findByIdPrueba", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p WHERE p.pruebaJornadaAulaAspiranteOpcionPK.idPrueba = :idPrueba"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findByIdJornada", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p WHERE p.pruebaJornadaAulaAspiranteOpcionPK.idJornada = :idJornada"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findByIdAula", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p WHERE p.pruebaJornadaAulaAspiranteOpcionPK.idAula = :idAula"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findByIdAspiranteOpcion", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p WHERE p.pruebaJornadaAulaAspiranteOpcionPK.idAspiranteOpcion = :idAspiranteOpcion"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findByActivo", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p WHERE p.activo = :activo"),
    @NamedQuery(name = "PruebaJornadaAulaAspiranteOpcion.findByFecha", query = "SELECT p FROM PruebaJornadaAulaAspiranteOpcion p WHERE p.fecha = :fecha")})
public class PruebaJornadaAulaAspiranteOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaJornadaAulaAspiranteOpcionPK pruebaJornadaAulaAspiranteOpcionPK;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pruebaJornadaAulaAspiranteOpcion", fetch = FetchType.LAZY)
    private PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamen;
    @JoinColumn(name = "id_aspirante_opcion", referencedColumnName = "id_aspirante_opcion", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AspiranteOpcion aspiranteOpcion;
    @JoinColumns({
        @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada", insertable = false, updatable = false),
        @JoinColumn(name = "id_aula", referencedColumnName = "id_aula", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JornadaAula jornadaAula;
    @JoinColumn(name = "id_prueba", referencedColumnName = "id_prueba", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Prueba prueba;

    public PruebaJornadaAulaAspiranteOpcion() {
    }

    public PruebaJornadaAulaAspiranteOpcion(PruebaJornadaAulaAspiranteOpcionPK pruebaJornadaAulaAspiranteOpcionPK) {
        this.pruebaJornadaAulaAspiranteOpcionPK = pruebaJornadaAulaAspiranteOpcionPK;
    }

    public PruebaJornadaAulaAspiranteOpcion(long idPrueba, long idJornada, String idAula, long idAspiranteOpcion) {
        this.pruebaJornadaAulaAspiranteOpcionPK = new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion);
    }

    public PruebaJornadaAulaAspiranteOpcionPK getPruebaJornadaAulaAspiranteOpcionPK() {
        return pruebaJornadaAulaAspiranteOpcionPK;
    }

    public void setPruebaJornadaAulaAspiranteOpcionPK(PruebaJornadaAulaAspiranteOpcionPK pruebaJornadaAulaAspiranteOpcionPK) {
        this.pruebaJornadaAulaAspiranteOpcionPK = pruebaJornadaAulaAspiranteOpcionPK;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PruebaJornadaAulaAspiranteOpcionExamen getPruebaJornadaAulaAspiranteOpcionExamen() {
        return pruebaJornadaAulaAspiranteOpcionExamen;
    }

    public void setPruebaJornadaAulaAspiranteOpcionExamen(PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamen) {
        this.pruebaJornadaAulaAspiranteOpcionExamen = pruebaJornadaAulaAspiranteOpcionExamen;
    }

    public AspiranteOpcion getAspiranteOpcion() {
        return aspiranteOpcion;
    }

    public void setAspiranteOpcion(AspiranteOpcion aspiranteOpcion) {
        this.aspiranteOpcion = aspiranteOpcion;
    }

    public JornadaAula getJornadaAula() {
        return jornadaAula;
    }

    public void setJornadaAula(JornadaAula jornadaAula) {
        this.jornadaAula = jornadaAula;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaJornadaAulaAspiranteOpcionPK != null ? pruebaJornadaAulaAspiranteOpcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaJornadaAulaAspiranteOpcion)) {
            return false;
        }
        PruebaJornadaAulaAspiranteOpcion other = (PruebaJornadaAulaAspiranteOpcion) object;
        if ((this.pruebaJornadaAulaAspiranteOpcionPK == null && other.pruebaJornadaAulaAspiranteOpcionPK != null) || (this.pruebaJornadaAulaAspiranteOpcionPK != null && !this.pruebaJornadaAulaAspiranteOpcionPK.equals(other.pruebaJornadaAulaAspiranteOpcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion[ pruebaJornadaAulaAspiranteOpcionPK=" + pruebaJornadaAulaAspiranteOpcionPK + " ]";
    }

}
