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
public class CursoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASI_ID_ASIGNATURA")
    private int asiIdAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIP_ID_TIPO_ASIGNATURA")
    private int tipIdTipoAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEM_ID_FECHA")
    private int semIdFecha;

    public CursoPK() {
    }

    public CursoPK(int asiIdAsignatura, int tipIdTipoAsignatura, int semIdFecha) {
        this.asiIdAsignatura = asiIdAsignatura;
        this.tipIdTipoAsignatura = tipIdTipoAsignatura;
        this.semIdFecha = semIdFecha;
    }

    public int getAsiIdAsignatura() {
        return asiIdAsignatura;
    }

    public void setAsiIdAsignatura(int asiIdAsignatura) {
        this.asiIdAsignatura = asiIdAsignatura;
    }

    public int getTipIdTipoAsignatura() {
        return tipIdTipoAsignatura;
    }

    public void setTipIdTipoAsignatura(int tipIdTipoAsignatura) {
        this.tipIdTipoAsignatura = tipIdTipoAsignatura;
    }

    public int getSemIdFecha() {
        return semIdFecha;
    }

    public void setSemIdFecha(int semIdFecha) {
        this.semIdFecha = semIdFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) asiIdAsignatura;
        hash += (int) tipIdTipoAsignatura;
        hash += (int) semIdFecha;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoPK)) {
            return false;
        }
        CursoPK other = (CursoPK) object;
        if (this.asiIdAsignatura != other.asiIdAsignatura) {
            return false;
        }
        if (this.tipIdTipoAsignatura != other.tipIdTipoAsignatura) {
            return false;
        }
        if (this.semIdFecha != other.semIdFecha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CursoPK[ asiIdAsignatura=" + asiIdAsignatura + ", tipIdTipoAsignatura=" + tipIdTipoAsignatura + ", semIdFecha=" + semIdFecha + " ]";
    }
    
}
