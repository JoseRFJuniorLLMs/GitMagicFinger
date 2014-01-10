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
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByUniversidad", query = "SELECT d FROM Alumno d WHERE d.uniIdUniversidad.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Alumno.findByIdAlumno", query = "SELECT a FROM Alumno a WHERE a.idAlumno = :idAlumno")})
public class Alumno implements Serializable {
    @Lob
    @Column(name = "HUELLA1")
    private byte[] huella1;
    @Lob
    @Column(name = "HUELLA2")
    private byte[] huella2;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALUMNO")
    private Integer idAlumno;
    @Lob
    @Size(max = 65535)
    @Column(name = "RUT")
    private String rut;
    @Lob
    @Size(max = 65535)
    @Column(name = "NOMBRE")
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "APELLIDOP")
    private String apellidop;
    @Lob
    @Size(max = 65535)
    @Column(name = "APELLIDOM")
    private String apellidom;
    @Lob
    @Size(max = 65535)
    @Column(name = "TELEFONO")
    private String telefono;
    @Lob
    @Size(max = 65535)
    @Column(name = "CORREO")
    private String correo;
    @JoinColumn(name = "USE_ID", referencedColumnName = "ID")
    @ManyToOne
    private User useId;
    @JoinColumn(name = "UNI_ID_UNIVERSIDAD", referencedColumnName = "ID_UNIVERSIDAD")
    @ManyToOne(optional = false)
    private Universidad uniIdUniversidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<AlumnosDelCurso> alumnosDelCursoList;
    @OneToMany(mappedBy = "aluIdAlumno")
    private List<User> userList;

    public Alumno() {
    }

    public Alumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public User getUseId() {
        return useId;
    }

    public void setUseId(User useId) {
        this.useId = useId;
    }

    public Universidad getUniIdUniversidad() {
        return uniIdUniversidad;
    }

    public void setUniIdUniversidad(Universidad uniIdUniversidad) {
        this.uniIdUniversidad = uniIdUniversidad;
    }

    @XmlTransient
    public List<AlumnosDelCurso> getAlumnosDelCursoList() {
        return alumnosDelCursoList;
    }

    public void setAlumnosDelCursoList(List<AlumnosDelCurso> alumnosDelCursoList) {
        this.alumnosDelCursoList = alumnosDelCursoList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumno != null ? idAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.idAlumno == null && other.idAlumno != null) || (this.idAlumno != null && !this.idAlumno.equals(other.idAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Alumno[ idAlumno=" + idAlumno + " ]";
    }

    public byte[] getHuella1() {
        return huella1;
    }

    public void setHuella1(byte[] huella1) {
        this.huella1 = huella1;
    }

    public byte[] getHuella2() {
        return huella2;
    }

    public void setHuella2(byte[] huella2) {
        this.huella2 = huella2;
    }
    
}
