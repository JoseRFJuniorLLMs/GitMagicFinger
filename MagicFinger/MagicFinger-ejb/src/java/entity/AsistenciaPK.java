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
public class AsistenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID2")
    private int alumnosDelCursoId2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID3")
    private int alumnosDelCursoId3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALUMNOS_DEL_CURSO_ID")
    private int alumnosDelCursoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BLOQUE_CLASE_ID")
    private int bloqueClaseId;

    public AsistenciaPK() {
    }

    public AsistenciaPK(int alumnosDelCursoId2, int alumnosDelCursoId3, int alumnosDelCursoId, int bloqueClaseId) {
        this.alumnosDelCursoId2 = alumnosDelCursoId2;
        this.alumnosDelCursoId3 = alumnosDelCursoId3;
        this.alumnosDelCursoId = alumnosDelCursoId;
        this.bloqueClaseId = bloqueClaseId;
    }

    public int getAlumnosDelCursoId2() {
        return alumnosDelCursoId2;
    }

    public void setAlumnosDelCursoId2(int alumnosDelCursoId2) {
        this.alumnosDelCursoId2 = alumnosDelCursoId2;
    }

    public int getAlumnosDelCursoId3() {
        return alumnosDelCursoId3;
    }

    public void setAlumnosDelCursoId3(int alumnosDelCursoId3) {
        this.alumnosDelCursoId3 = alumnosDelCursoId3;
    }

    public int getAlumnosDelCursoId() {
        return alumnosDelCursoId;
    }

    public void setAlumnosDelCursoId(int alumnosDelCursoId) {
        this.alumnosDelCursoId = alumnosDelCursoId;
    }

    public int getBloqueClaseId() {
        return bloqueClaseId;
    }

    public void setBloqueClaseId(int bloqueClaseId) {
        this.bloqueClaseId = bloqueClaseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) alumnosDelCursoId2;
        hash += (int) alumnosDelCursoId3;
        hash += (int) alumnosDelCursoId;
        hash += (int) bloqueClaseId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaPK)) {
            return false;
        }
        AsistenciaPK other = (AsistenciaPK) object;
        if (this.alumnosDelCursoId2 != other.alumnosDelCursoId2) {
            return false;
        }
        if (this.alumnosDelCursoId3 != other.alumnosDelCursoId3) {
            return false;
        }
        if (this.alumnosDelCursoId != other.alumnosDelCursoId) {
            return false;
        }
        if (this.bloqueClaseId != other.bloqueClaseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AsistenciaPK[ alumnosDelCursoId2=" + alumnosDelCursoId2 + ", alumnosDelCursoId3=" + alumnosDelCursoId3 + ", alumnosDelCursoId=" + alumnosDelCursoId + ", bloqueClaseId=" + bloqueClaseId + " ]";
    }
    
}
