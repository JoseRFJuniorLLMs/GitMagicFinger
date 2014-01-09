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
    @Column(name = "CUR_ASI_ID_ASIGNATURA")
    private int curAsiIdAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUR_TIP_ID_TIPO_ASIGNATURA")
    private int curTipIdTipoAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUR_SEM_ID_FECHA")
    private int curSemIdFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_ID_PROFESOR")
    private int proIdProfesor;

    public ProfesoresPorCursoPK() {
    }

    public ProfesoresPorCursoPK(int curAsiIdAsignatura, int curTipIdTipoAsignatura, int curSemIdFecha, int proIdProfesor) {
        this.curAsiIdAsignatura = curAsiIdAsignatura;
        this.curTipIdTipoAsignatura = curTipIdTipoAsignatura;
        this.curSemIdFecha = curSemIdFecha;
        this.proIdProfesor = proIdProfesor;
    }

    public int getCurAsiIdAsignatura() {
        return curAsiIdAsignatura;
    }

    public void setCurAsiIdAsignatura(int curAsiIdAsignatura) {
        this.curAsiIdAsignatura = curAsiIdAsignatura;
    }

    public int getCurTipIdTipoAsignatura() {
        return curTipIdTipoAsignatura;
    }

    public void setCurTipIdTipoAsignatura(int curTipIdTipoAsignatura) {
        this.curTipIdTipoAsignatura = curTipIdTipoAsignatura;
    }

    public int getCurSemIdFecha() {
        return curSemIdFecha;
    }

    public void setCurSemIdFecha(int curSemIdFecha) {
        this.curSemIdFecha = curSemIdFecha;
    }

    public int getProIdProfesor() {
        return proIdProfesor;
    }

    public void setProIdProfesor(int proIdProfesor) {
        this.proIdProfesor = proIdProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) curAsiIdAsignatura;
        hash += (int) curTipIdTipoAsignatura;
        hash += (int) curSemIdFecha;
        hash += (int) proIdProfesor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesoresPorCursoPK)) {
            return false;
        }
        ProfesoresPorCursoPK other = (ProfesoresPorCursoPK) object;
        if (this.curAsiIdAsignatura != other.curAsiIdAsignatura) {
            return false;
        }
        if (this.curTipIdTipoAsignatura != other.curTipIdTipoAsignatura) {
            return false;
        }
        if (this.curSemIdFecha != other.curSemIdFecha) {
            return false;
        }
        if (this.proIdProfesor != other.proIdProfesor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProfesoresPorCursoPK[ curAsiIdAsignatura=" + curAsiIdAsignatura + ", curTipIdTipoAsignatura=" + curTipIdTipoAsignatura + ", curSemIdFecha=" + curSemIdFecha + ", proIdProfesor=" + proIdProfesor + " ]";
    }
    
}
