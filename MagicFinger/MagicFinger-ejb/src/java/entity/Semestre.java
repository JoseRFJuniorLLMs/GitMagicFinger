/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "semestre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semestre.findAll", query = "SELECT s FROM Semestre s"),
    @NamedQuery(name = "Semestre.findByIdFecha", query = "SELECT s FROM Semestre s WHERE s.idFecha = :idFecha"),
    @NamedQuery(name = "Semestre.findByFechaInicio", query = "SELECT s FROM Semestre s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Semestre.findByFechaTermino", query = "SELECT s FROM Semestre s WHERE s.fechaTermino = :fechaTermino"),
    @NamedQuery(name = "Semestre.findByNumSemestre", query = "SELECT s FROM Semestre s WHERE s.numSemestre = :numSemestre"),
    @NamedQuery(name = "Semestre.findByAnoSemestre", query = "SELECT s FROM Semestre s WHERE s.anoSemestre = :anoSemestre")})
public class Semestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FECHA")
    private Integer idFecha;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_TERMINO")
    @Temporal(TemporalType.DATE)
    private Date fechaTermino;
    @Column(name = "NUM_SEMESTRE")
    private Integer numSemestre;
    @Column(name = "ANO_SEMESTRE")
    private Integer anoSemestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semestre")
    private List<Curso> cursoList;

    public Semestre() {
    }

    public Semestre(Integer idFecha) {
        this.idFecha = idFecha;
    }

    public Integer getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(Integer idFecha) {
        this.idFecha = idFecha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Integer getNumSemestre() {
        return numSemestre;
    }

    public void setNumSemestre(Integer numSemestre) {
        this.numSemestre = numSemestre;
    }

    public Integer getAnoSemestre() {
        return anoSemestre;
    }

    public void setAnoSemestre(Integer anoSemestre) {
        this.anoSemestre = anoSemestre;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFecha != null ? idFecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semestre)) {
            return false;
        }
        Semestre other = (Semestre) object;
        if ((this.idFecha == null && other.idFecha != null) || (this.idFecha != null && !this.idFecha.equals(other.idFecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return anoSemestre + "-"+numSemestre ;
    }
    
}
