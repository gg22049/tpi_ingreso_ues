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
import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "distractor")
@NamedQueries({
    @NamedQuery(name = "Distractor.findAll", query = "SELECT d FROM Distractor d"),
    @NamedQuery(name = "Distractor.findByIdDistractor", query = "SELECT d FROM Distractor d WHERE d.idDistractor = :idDistractor"),
    @NamedQuery(name = "Distractor.findByValor", query = "SELECT d FROM Distractor d WHERE d.valor = :valor"),
    @NamedQuery(name = "Distractor.findByObservaciones", query = "SELECT d FROM Distractor d WHERE d.observaciones = :observaciones")})
public class Distractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distractor")
    private Long idDistractor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "valor")
    private String valor;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDistractor")
    private List<DistractorAreaConocimiento> distractorAreaConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDistractor")
    private List<PreguntaDistractor> preguntaDistractorList;

    public Distractor() {
    }

    public Distractor(Long idDistractor) {
        this.idDistractor = idDistractor;
    }

    public Distractor(Long idDistractor, String valor) {
        this.idDistractor = idDistractor;
        this.valor = valor;
    }

    public Long getIdDistractor() {
        return idDistractor;
    }

    public void setIdDistractor(Long idDistractor) {
        this.idDistractor = idDistractor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @JsonbTransient
    public List<DistractorAreaConocimiento> getDistractorAreaConocimientoList() {
        return distractorAreaConocimientoList;
    }

    public void setDistractorAreaConocimientoList(List<DistractorAreaConocimiento> distractorAreaConocimientoList) {
        this.distractorAreaConocimientoList = distractorAreaConocimientoList;
    }

    @JsonbTransient
    public List<PreguntaDistractor> getPreguntaDistractorList() {
        return preguntaDistractorList;
    }

    public void setPreguntaDistractorList(List<PreguntaDistractor> preguntaDistractorList) {
        this.preguntaDistractorList = preguntaDistractorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistractor != null ? idDistractor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distractor)) {
            return false;
        }
        Distractor other = (Distractor) object;
        if ((this.idDistractor == null && other.idDistractor != null) || (this.idDistractor != null && !this.idDistractor.equals(other.idDistractor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor[ idDistractor=" + idDistractor + " ]";
    }

}
