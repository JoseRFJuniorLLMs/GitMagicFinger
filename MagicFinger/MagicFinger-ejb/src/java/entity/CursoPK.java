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
    @Column(name = "ASIGNATURA_ID")
    private int asignaturaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_ASIGNATURA_ID")
    private int tipoAsignaturaId;

    public CursoPK() {
    }

    public CursoPK(int asignaturaId, int tipoAsignaturaId) {
        this.asignaturaId = asignaturaId;
        this.tipoAsignaturaId = tipoAsignaturaId;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public int getTipoAsignaturaId() {
        return tipoAsignaturaId;
    }

    public void setTipoAsignaturaId(int tipoAsignaturaId) {
        this.tipoAsignaturaId = tipoAsignaturaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) asignaturaId;
        hash += (int) tipoAsignaturaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoPK)) {
            return false;
        }
        CursoPK other = (CursoPK) object;
        if (this.asignaturaId != other.asignaturaId) {
            return false;
        }
        if (this.tipoAsignaturaId != other.tipoAsignaturaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CursoPK[ asignaturaId=" + asignaturaId + ", tipoAsignaturaId=" + tipoAsignaturaId + " ]";
    }
    
}
