/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "tipo_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAsignatura.findAll", query = "SELECT t FROM TipoAsignatura t"),
    @NamedQuery(name = "TipoAsignatura.findByIdTipoAsignatura", query = "SELECT t FROM TipoAsignatura t WHERE t.idTipoAsignatura = :idTipoAsignatura")})
public class TipoAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ASIGNATURA")
    private Integer idTipoAsignatura;
    @Lob
    @Size(max = 65535)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAsignatura")
    private List<Curso> cursoList;

    public TipoAsignatura() {
    }

    public TipoAsignatura(Integer idTipoAsignatura) {
        this.idTipoAsignatura = idTipoAsignatura;
    }

    public Integer getIdTipoAsignatura() {
        return idTipoAsignatura;
    }

    public void setIdTipoAsignatura(Integer idTipoAsignatura) {
        this.idTipoAsignatura = idTipoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAsignatura != null ? idTipoAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsignatura)) {
            return false;
        }
        TipoAsignatura other = (TipoAsignatura) object;
        if ((this.idTipoAsignatura == null && other.idTipoAsignatura != null) || (this.idTipoAsignatura != null && !this.idTipoAsignatura.equals(other.idTipoAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
