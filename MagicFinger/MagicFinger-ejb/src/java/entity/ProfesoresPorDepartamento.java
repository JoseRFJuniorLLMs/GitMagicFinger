/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "profesores_por_departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesoresPorDepartamento.findAll", query = "SELECT p FROM ProfesoresPorDepartamento p"),
    @NamedQuery(name = "ProfesoresPorDepartamento.findByUniversidad", query = "SELECT d FROM ProfesoresPorDepartamento d WHERE d.departamento.facIdFacultad.uniIdUniversidad.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "ProfesoresPorDepartamento.findByProIdProfesor", query = "SELECT p FROM ProfesoresPorDepartamento p WHERE p.profesoresPorDepartamentoPK.proIdProfesor = :proIdProfesor"),
    @NamedQuery(name = "ProfesoresPorDepartamento.findByDepIdDepartamento", query = "SELECT p FROM ProfesoresPorDepartamento p WHERE p.profesoresPorDepartamentoPK.depIdDepartamento = :depIdDepartamento"),
    @NamedQuery(name = "ProfesoresPorDepartamento.findByIdProfDep", query = "SELECT p FROM ProfesoresPorDepartamento p WHERE p.idProfDep = :idProfDep")})
public class ProfesoresPorDepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProfesoresPorDepartamentoPK profesoresPorDepartamentoPK;
    @Column(name = "ID_PROF_DEP")
    private Integer idProfDep;
    @JoinColumn(name = "PRO_ID_PROFESOR", referencedColumnName = "ID_PROFESOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profesor profesor;
    @JoinColumn(name = "DEP_ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Departamento departamento;

    public ProfesoresPorDepartamento() {
    }

    public ProfesoresPorDepartamento(ProfesoresPorDepartamentoPK profesoresPorDepartamentoPK) {
        this.profesoresPorDepartamentoPK = profesoresPorDepartamentoPK;
    }

    public ProfesoresPorDepartamento(int proIdProfesor, int depIdDepartamento) {
        this.profesoresPorDepartamentoPK = new ProfesoresPorDepartamentoPK(proIdProfesor, depIdDepartamento);
    }

    public ProfesoresPorDepartamentoPK getProfesoresPorDepartamentoPK() {
        return profesoresPorDepartamentoPK;
    }

    public void setProfesoresPorDepartamentoPK(ProfesoresPorDepartamentoPK profesoresPorDepartamentoPK) {
        this.profesoresPorDepartamentoPK = profesoresPorDepartamentoPK;
    }

    public Integer getIdProfDep() {
        return idProfDep;
    }

    public void setIdProfDep(Integer idProfDep) {
        this.idProfDep = idProfDep;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profesoresPorDepartamentoPK != null ? profesoresPorDepartamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesoresPorDepartamento)) {
            return false;
        }
        ProfesoresPorDepartamento other = (ProfesoresPorDepartamento) object;
        if ((this.profesoresPorDepartamentoPK == null && other.profesoresPorDepartamentoPK != null) || (this.profesoresPorDepartamentoPK != null && !this.profesoresPorDepartamentoPK.equals(other.profesoresPorDepartamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProfesoresPorDepartamento[ profesoresPorDepartamentoPK=" + profesoresPorDepartamentoPK + " ]";
    }
    
}
