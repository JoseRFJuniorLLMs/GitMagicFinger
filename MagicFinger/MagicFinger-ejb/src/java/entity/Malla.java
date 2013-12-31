/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "malla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Malla.findAll", query = "SELECT m FROM Malla m"),
    @NamedQuery(name = "Malla.findByIdCarrera", query = "SELECT m FROM Malla m WHERE m.idCarrera = :idCarrera"),
    @NamedQuery(name = "Malla.findByFecha", query = "SELECT m FROM Malla m WHERE m.fecha = :fecha")})
public class Malla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARRERA")
    private Integer idCarrera;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "CARRERA_ID", referencedColumnName = "ID_CARR")
    @ManyToOne(optional = false)
    private Carrera carreraId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mallaId")
    private List<Asignatura> asignaturaList;

    public Malla() {
    }

    public Malla(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Carrera getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Carrera carreraId) {
        this.carreraId = carreraId;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrera != null ? idCarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Malla)) {
            return false;
        }
        Malla other = (Malla) object;
        if ((this.idCarrera == null && other.idCarrera != null) || (this.idCarrera != null && !this.idCarrera.equals(other.idCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Malla[ idCarrera=" + idCarrera + " ]";
    }
    
}
