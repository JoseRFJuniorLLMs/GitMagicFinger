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
public class GruposPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID4")
    private int alumnosDelCursoId4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID3")
    private int alumnosDelCursoId3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID2")
    private int alumnosDelCursoId2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID")
    private int alumnosDelCursoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURSO_ID3")
    private int cursoId3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURSO_ID2")
    private int cursoId2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURSO_ID")
    private int cursoId;

    public GruposPK() {
    }

    public GruposPK(int alumnosDelCursoId4, int alumnosDelCursoId3, int alumnosDelCursoId2, int alumnosDelCursoId, int cursoId3, int cursoId2, int cursoId) {
        this.alumnosDelCursoId4 = alumnosDelCursoId4;
        this.alumnosDelCursoId3 = alumnosDelCursoId3;
        this.alumnosDelCursoId2 = alumnosDelCursoId2;
        this.alumnosDelCursoId = alumnosDelCursoId;
        this.cursoId3 = cursoId3;
        this.cursoId2 = cursoId2;
        this.cursoId = cursoId;
    }

    public int getAlumnosDelCursoId4() {
        return alumnosDelCursoId4;
    }

    public void setAlumnosDelCursoId4(int alumnosDelCursoId4) {
        this.alumnosDelCursoId4 = alumnosDelCursoId4;
    }

    public int getAlumnosDelCursoId3() {
        return alumnosDelCursoId3;
    }

    public void setAlumnosDelCursoId3(int alumnosDelCursoId3) {
        this.alumnosDelCursoId3 = alumnosDelCursoId3;
    }

    public int getAlumnosDelCursoId2() {
        return alumnosDelCursoId2;
    }

    public void setAlumnosDelCursoId2(int alumnosDelCursoId2) {
        this.alumnosDelCursoId2 = alumnosDelCursoId2;
    }

    public int getAlumnosDelCursoId() {
        return alumnosDelCursoId;
    }

    public void setAlumnosDelCursoId(int alumnosDelCursoId) {
        this.alumnosDelCursoId = alumnosDelCursoId;
    }

    public int getCursoId3() {
        return cursoId3;
    }

    public void setCursoId3(int cursoId3) {
        this.cursoId3 = cursoId3;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) alumnosDelCursoId4;
        hash += (int) alumnosDelCursoId3;
        hash += (int) alumnosDelCursoId2;
        hash += (int) alumnosDelCursoId;
        hash += (int) cursoId3;
        hash += (int) cursoId2;
        hash += (int) cursoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruposPK)) {
            return false;
        }
        GruposPK other = (GruposPK) object;
        if (this.alumnosDelCursoId4 != other.alumnosDelCursoId4) {
            return false;
        }
        if (this.alumnosDelCursoId3 != other.alumnosDelCursoId3) {
            return false;
        }
        if (this.alumnosDelCursoId2 != other.alumnosDelCursoId2) {
            return false;
        }
        if (this.alumnosDelCursoId != other.alumnosDelCursoId) {
            return false;
        }
        if (this.cursoId3 != other.cursoId3) {
            return false;
        }
        if (this.cursoId2 != other.cursoId2) {
            return false;
        }
        if (this.cursoId != other.cursoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GruposPK[ alumnosDelCursoId4=" + alumnosDelCursoId4 + ", alumnosDelCursoId3=" + alumnosDelCursoId3 + ", alumnosDelCursoId2=" + alumnosDelCursoId2 + ", alumnosDelCursoId=" + alumnosDelCursoId + ", cursoId3=" + cursoId3 + ", cursoId2=" + cursoId2 + ", cursoId=" + cursoId + " ]";
    }
    
}
