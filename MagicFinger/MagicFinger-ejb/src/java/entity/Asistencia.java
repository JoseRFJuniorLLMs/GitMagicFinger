/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "asistencia")
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByAlumnosDelCursoId2", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.alumnosDelCursoId2 = :alumnosDelCursoId2"),
    @NamedQuery(name = "Asistencia.findByAlumnosDelCursoId3", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.alumnosDelCursoId3 = :alumnosDelCursoId3"),
    @NamedQuery(name = "Asistencia.findByAlumnosDelCursoId", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.alumnosDelCursoId = :alumnosDelCursoId"),
    @NamedQuery(name = "Asistencia.findByBloqueClaseId", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.bloqueClaseId = :bloqueClaseId"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findByEstado", query = "SELECT a FROM Asistencia a WHERE a.estado = :estado")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsistenciaPK asistenciaPK;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "ESTADO")
    private Integer estado;
    @JoinColumns({
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID2", referencedColumnName = "CURSO_ID2", insertable = false, updatable = false),
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID3", referencedColumnName = "CURSO_ID", insertable = false, updatable = false),
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID", referencedColumnName = "ALUMNO_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private AlumnosPorCurso alumnosPorCurso;
    @JoinColumn(name = "BLOQUE_CLASE_ID", referencedColumnName = "ID_BLOQUE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BloqueClase bloqueClase;

    public Asistencia() {
    }

    public Asistencia(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
    }

    public Asistencia(int alumnosDelCursoId2, int alumnosDelCursoId3, int alumnosDelCursoId, int bloqueClaseId) {
        this.asistenciaPK = new AsistenciaPK(alumnosDelCursoId2, alumnosDelCursoId3, alumnosDelCursoId, bloqueClaseId);
    }

    public AsistenciaPK getAsistenciaPK() {
        return asistenciaPK;
    }

    public void setAsistenciaPK(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
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

    public AlumnosPorCurso getAlumnosPorCurso() {
        return alumnosPorCurso;
    }

    public void setAlumnosPorCurso(AlumnosPorCurso alumnosPorCurso) {
        this.alumnosPorCurso = alumnosPorCurso;
    }

    public BloqueClase getBloqueClase() {
        return bloqueClase;
    }

    public void setBloqueClase(BloqueClase bloqueClase) {
        this.bloqueClase = bloqueClase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asistenciaPK != null ? asistenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.asistenciaPK == null && other.asistenciaPK != null) || (this.asistenciaPK != null && !this.asistenciaPK.equals(other.asistenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asistencia[ asistenciaPK=" + asistenciaPK + " ]";
    }
    
}
