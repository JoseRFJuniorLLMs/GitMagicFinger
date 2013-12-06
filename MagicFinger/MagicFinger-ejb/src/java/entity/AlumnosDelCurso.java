/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "alumnos_del_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlumnosDelCurso.findAll", query = "SELECT a FROM AlumnosDelCurso a"),
    @NamedQuery(name = "AlumnosDelCurso.findByCursoId2", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.cursoId2 = :cursoId2"),
    @NamedQuery(name = "AlumnosDelCurso.findByCursoId", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.cursoId = :cursoId"),
    @NamedQuery(name = "AlumnosDelCurso.findByAlumnoId", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.alumnoId = :alumnoId")})
public class AlumnosDelCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumnosDelCursoPK alumnosDelCursoPK;
    @JoinColumns({
        @JoinColumn(name = "CURSO_ID2", referencedColumnName = "ASIGNATURA_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CURSO_ID", referencedColumnName = "TIPO_ASIGNATURA_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "ALUMNO_ID", referencedColumnName = "ID_ALUMNO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumnosDelCurso")
    private List<Asistencia> asistenciaList;

    public AlumnosDelCurso() {
    }

    public AlumnosDelCurso(AlumnosDelCursoPK alumnosDelCursoPK) {
        this.alumnosDelCursoPK = alumnosDelCursoPK;
    }

    public AlumnosDelCurso(int cursoId2, int cursoId, int alumnoId) {
        this.alumnosDelCursoPK = new AlumnosDelCursoPK(cursoId2, cursoId, alumnoId);
    }

    public AlumnosDelCursoPK getAlumnosDelCursoPK() {
        return alumnosDelCursoPK;
    }

    public void setAlumnosDelCursoPK(AlumnosDelCursoPK alumnosDelCursoPK) {
        this.alumnosDelCursoPK = alumnosDelCursoPK;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnosDelCursoPK != null ? alumnosDelCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnosDelCurso)) {
            return false;
        }
        AlumnosDelCurso other = (AlumnosDelCurso) object;
        if ((this.alumnosDelCursoPK == null && other.alumnosDelCursoPK != null) || (this.alumnosDelCursoPK != null && !this.alumnosDelCursoPK.equals(other.alumnosDelCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AlumnosDelCurso[ alumnosDelCursoPK=" + alumnosDelCursoPK + " ]";
    }
    
}
