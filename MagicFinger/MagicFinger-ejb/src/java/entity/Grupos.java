/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g"),
    @NamedQuery(name = "Grupos.findByUniversidad", query = "SELECT d FROM Grupos d WHERE d.curso.tipoAsignatura.uniIdUniversidad.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Grupos.findByIdGrupo", query = "SELECT g FROM Grupos g WHERE g.idGrupo = :idGrupo")})
public class Grupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO")
    private Integer idGrupo;
    @Lob
    @Size(max = 65535)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "gruIdGrupo")
    private List<AlumnosDelCurso> alumnosDelCursoList;
    @JoinColumns({
        @JoinColumn(name = "CUR_ASI_ID_ASIGNATURA", referencedColumnName = "ASI_ID_ASIGNATURA"),
        @JoinColumn(name = "CUR_TIP_ID_TIPO_ASIGNATURA", referencedColumnName = "TIP_ID_TIPO_ASIGNATURA"),
        @JoinColumn(name = "CUR_SEM_ID_FECHA", referencedColumnName = "SEM_ID_FECHA")})
    @ManyToOne
    private Curso curso;

    public Grupos() {
    }

    public Grupos(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<AlumnosDelCurso> getAlumnosDelCursoList() {
        return alumnosDelCursoList;
    }

    public void setAlumnosDelCursoList(List<AlumnosDelCurso> alumnosDelCursoList) {
        this.alumnosDelCursoList = alumnosDelCursoList;
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
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
