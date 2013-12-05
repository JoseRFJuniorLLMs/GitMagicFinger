/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chevo
 */
@Embeddable
public class ProfesoresPorCursoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURSO_ID2")
    private int cursoId2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURSO_ID")
    private int cursoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROFESOR_ID")
    private int profesorId;

    public ProfesoresPorCursoPK() {
    }

    public ProfesoresPorCursoPK(int cursoId2, int cursoId, int profesorId) {
        this.cursoId2 = cursoId2;
        this.cursoId = cursoId;
        this.profesorId = profesorId;
    }

    public int getCursoId2() {
        return cursoId2;
    }

    public void setCursoId2(int cursoId2) {
        this.cursoId2 = cursoId2;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cursoId2;
        hash += (int) cursoId;
        hash += (int) profesorId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesoresPorCursoPK)) {
            return false;
        }
        ProfesoresPorCursoPK other = (ProfesoresPorCursoPK) object;
        if (this.cursoId2 != other.cursoId2) {
            return false;
        }
        if (this.cursoId != other.cursoId) {
            return false;
        }
        if (this.profesorId != other.profesorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProfesoresPorCursoPK[ cursoId2=" + cursoId2 + ", cursoId=" + cursoId + ", profesorId=" + profesorId + " ]";
    }
    
}
