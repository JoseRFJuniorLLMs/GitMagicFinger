/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findByEstado", query = "SELECT a FROM Asistencia a WHERE a.estado = :estado")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASISTENCIA")
    private Integer idAsistencia;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "ESTADO")
    private Integer estado;
    @JoinColumns({
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID2", referencedColumnName = "CURSO_ID2"),
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID3", referencedColumnName = "CURSO_ID"),
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID", referencedColumnName = "ALUMNO_ID")})
    @ManyToOne(optional = false)
    private AlumnosDelCurso alumnosDelCurso;
    @JoinColumn(name = "BLOQUE_CLASE_ID", referencedColumnName = "ID_BLOQUE")
    @ManyToOne(optional = false)
    private BloqueClase bloqueClaseId;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public AlumnosDelCurso getAlumnosDelCurso() {
        return alumnosDelCurso;
    }

    public void setAlumnosDelCurso(AlumnosDelCurso alumnosDelCurso) {
        this.alumnosDelCurso = alumnosDelCurso;
    }

    public BloqueClase getBloqueClaseId() {
        return bloqueClaseId;
    }

    public void setBloqueClaseId(BloqueClase bloqueClaseId) {
        this.bloqueClaseId = bloqueClaseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asistencia[ idAsistencia=" + idAsistencia + " ]";
    }
    
}
