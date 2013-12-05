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

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "alumnos_por_curso")
@NamedQueries({
    @NamedQuery(name = "AlumnosPorCurso.findAll", query = "SELECT a FROM AlumnosPorCurso a"),
    @NamedQuery(name = "AlumnosPorCurso.findByCursoId2", query = "SELECT a FROM AlumnosPorCurso a WHERE a.alumnosPorCursoPK.cursoId2 = :cursoId2"),
    @NamedQuery(name = "AlumnosPorCurso.findByCursoId", query = "SELECT a FROM AlumnosPorCurso a WHERE a.alumnosPorCursoPK.cursoId = :cursoId"),
    @NamedQuery(name = "AlumnosPorCurso.findByAlumnoId", query = "SELECT a FROM AlumnosPorCurso a WHERE a.alumnosPorCursoPK.alumnoId = :alumnoId")})
public class AlumnosPorCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumnosPorCursoPK alumnosPorCursoPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumnosPorCurso")
    private List<Asistencia> asistenciaList;
    @JoinColumns({
        @JoinColumn(name = "CURSO_ID2", referencedColumnName = "ASIGNATURA_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CURSO_ID", referencedColumnName = "TIPO_ASIGNATURA_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "ALUMNO_ID", referencedColumnName = "ID_ALUMNO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    public AlumnosPorCurso() {
    }

    public AlumnosPorCurso(AlumnosPorCursoPK alumnosPorCursoPK) {
        this.alumnosPorCursoPK = alumnosPorCursoPK;
    }

    public AlumnosPorCurso(int cursoId2, int cursoId, int alumnoId) {
        this.alumnosPorCursoPK = new AlumnosPorCursoPK(cursoId2, cursoId, alumnoId);
    }

    public AlumnosPorCursoPK getAlumnosPorCursoPK() {
        return alumnosPorCursoPK;
    }

    public void setAlumnosPorCursoPK(AlumnosPorCursoPK alumnosPorCursoPK) {
        this.alumnosPorCursoPK = alumnosPorCursoPK;
    }

    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnosPorCursoPK != null ? alumnosPorCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnosPorCurso)) {
            return false;
        }
        AlumnosPorCurso other = (AlumnosPorCurso) object;
        if ((this.alumnosPorCursoPK == null && other.alumnosPorCursoPK != null) || (this.alumnosPorCursoPK != null && !this.alumnosPorCursoPK.equals(other.alumnosPorCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AlumnosPorCurso[ alumnosPorCursoPK=" + alumnosPorCursoPK + " ]";
    }
    
}
