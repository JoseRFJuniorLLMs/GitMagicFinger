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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "USUARIO")
    private String usuario;
    @Lob
    @Size(max = 65535)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "useId")
    private List<Profesor> profesorList;
    @OneToMany(mappedBy = "useId")
    private List<Alumno> alumnoList;
    @JoinColumn(name = "PRO_ID_PROFESOR", referencedColumnName = "ID_PROFESOR")
    @ManyToOne
    private Profesor proIdProfesor;
    @JoinColumn(name = "ALU_ID_ALUMNO", referencedColumnName = "ID_ALUMNO")
    @ManyToOne
    private Alumno aluIdAlumno;
    @JoinColumn(name = "USE_ROL", referencedColumnName = "ROL")
    @ManyToOne(optional = false)
    private Userrol useRol;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    @XmlTransient
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    public Profesor getProIdProfesor() {
        return proIdProfesor;
    }

    public void setProIdProfesor(Profesor proIdProfesor) {
        this.proIdProfesor = proIdProfesor;
    }

    public Alumno getAluIdAlumno() {
        return aluIdAlumno;
    }

    public void setAluIdAlumno(Alumno aluIdAlumno) {
        this.aluIdAlumno = aluIdAlumno;
    }

    public Userrol getUseRol() {
        return useRol;
    }

    public void setUseRol(Userrol useRol) {
        this.useRol = useRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ id=" + id + " ]";
    }
    
}
