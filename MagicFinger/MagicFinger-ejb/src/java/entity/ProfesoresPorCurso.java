/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "profesores_por_curso")
@NamedQueries({
    @NamedQuery(name = "ProfesoresPorCurso.findAll", query = "SELECT p FROM ProfesoresPorCurso p"),
    @NamedQuery(name = "ProfesoresPorCurso.findByCursoId2", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.cursoId2 = :cursoId2"),
    @NamedQuery(name = "ProfesoresPorCurso.findByCursoId", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.cursoId = :cursoId"),
    @NamedQuery(name = "ProfesoresPorCurso.findByProfesorId", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.profesorId = :profesorId")})
public class ProfesoresPorCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProfesoresPorCursoPK profesoresPorCursoPK;
    @JoinColumn(name = "PROFESOR_ID", referencedColumnName = "ID_PROFESOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profesor profesor;
    @JoinColumns({
        @JoinColumn(name = "CURSO_ID2", referencedColumnName = "ASIGNATURA_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CURSO_ID", referencedColumnName = "TIPO_ASIGNATURA_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Curso curso;

    public ProfesoresPorCurso() {
    }

    public ProfesoresPorCurso(ProfesoresPorCursoPK profesoresPorCursoPK) {
        this.profesoresPorCursoPK = profesoresPorCursoPK;
    }

    public ProfesoresPorCurso(int cursoId2, int cursoId, int profesorId) {
        this.profesoresPorCursoPK = new ProfesoresPorCursoPK(cursoId2, cursoId, profesorId);
    }

    public ProfesoresPorCursoPK getProfesoresPorCursoPK() {
        return profesoresPorCursoPK;
    }

    public void setProfesoresPorCursoPK(ProfesoresPorCursoPK profesoresPorCursoPK) {
        this.profesoresPorCursoPK = profesoresPorCursoPK;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profesoresPorCursoPK != null ? profesoresPorCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesoresPorCurso)) {
            return false;
        }
        ProfesoresPorCurso other = (ProfesoresPorCurso) object;
        if ((this.profesoresPorCursoPK == null && other.profesoresPorCursoPK != null) || (this.profesoresPorCursoPK != null && !this.profesoresPorCursoPK.equals(other.profesoresPorCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProfesoresPorCurso[ profesoresPorCursoPK=" + profesoresPorCursoPK + " ]";
    }
    
}
