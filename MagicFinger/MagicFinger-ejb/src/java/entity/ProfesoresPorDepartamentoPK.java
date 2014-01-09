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
public class ProfesoresPorDepartamentoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_ID_PROFESOR")
    private int proIdProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEP_ID_DEPARTAMENTO")
    private int depIdDepartamento;

    public ProfesoresPorDepartamentoPK() {
    }

    public ProfesoresPorDepartamentoPK(int proIdProfesor, int depIdDepartamento) {
        this.proIdProfesor = proIdProfesor;
        this.depIdDepartamento = depIdDepartamento;
    }

    public int getProIdProfesor() {
        return proIdProfesor;
    }

    public void setProIdProfesor(int proIdProfesor) {
        this.proIdProfesor = proIdProfesor;
    }

    public int getDepIdDepartamento() {
        return depIdDepartamento;
    }

    public void setDepIdDepartamento(int depIdDepartamento) {
        this.depIdDepartamento = depIdDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proIdProfesor;
        hash += (int) depIdDepartamento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesoresPorDepartamentoPK)) {
            return false;
        }
        ProfesoresPorDepartamentoPK other = (ProfesoresPorDepartamentoPK) object;
        if (this.proIdProfesor != other.proIdProfesor) {
            return false;
        }
        if (this.depIdDepartamento != other.depIdDepartamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProfesoresPorDepartamentoPK[ proIdProfesor=" + proIdProfesor + ", depIdDepartamento=" + depIdDepartamento + " ]";
    }
    
}
