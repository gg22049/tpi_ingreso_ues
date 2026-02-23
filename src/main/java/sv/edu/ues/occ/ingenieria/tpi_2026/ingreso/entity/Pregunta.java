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
@Table(name = "pregunta")
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "Pregunta.findByEnunciado", query = "SELECT p FROM Pregunta p WHERE p.enunciado = :enunciado"),
    @NamedQuery(name = "Pregunta.findByRespuesta", query = "SELECT p FROM Pregunta p WHERE p.respuesta = :respuesta"),
    @NamedQuery(name = "Pregunta.findByObservaciones", query = "SELECT p FROM Pregunta p WHERE p.observaciones = :observaciones")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Long idPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "enunciado")
    private String enunciado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "respuesta")
    private String respuesta;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<PreguntaAreaConocimiento> preguntaAreaConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<ExamenAreaConocimientoPregunta> examenAreaConocimientoPreguntaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<PreguntaDistractor> preguntaDistractorList;

    public Pregunta() {
    }

    public Pregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Long idPregunta, String enunciado, String respuesta) {
        this.idPregunta = idPregunta;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @JsonbTransient
    public List<PreguntaAreaConocimiento> getPreguntaAreaConocimientoList() {
        return preguntaAreaConocimientoList;
    }

    public void setPreguntaAreaConocimientoList(List<PreguntaAreaConocimiento> preguntaAreaConocimientoList) {
        this.preguntaAreaConocimientoList = preguntaAreaConocimientoList;
    }

    @JsonbTransient
    public List<ExamenAreaConocimientoPregunta> getExamenAreaConocimientoPreguntaList() {
        return examenAreaConocimientoPreguntaList;
    }

    public void setExamenAreaConocimientoPreguntaList(List<ExamenAreaConocimientoPregunta> examenAreaConocimientoPreguntaList) {
        this.examenAreaConocimientoPreguntaList = examenAreaConocimientoPreguntaList;
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
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta[ idPregunta=" + idPregunta + " ]";
    }

}
