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
    @NamedQuery(name = "Malla.findByUniversidad", query = "SELECT m FROM Malla m WHERE m.carIdCarrera.depIdDepartamento.facIdFacultad.uniIdUniversidad.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Malla.findByIdMalla", query = "SELECT m FROM Malla m WHERE m.idMalla = :idMalla"),
    @NamedQuery(name = "Malla.findByFecha", query = "SELECT m FROM Malla m WHERE m.fecha = :fecha")})
public class Malla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MALLA")
    private Integer idMalla;
    @Lob
    @Size(max = 65535)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "CAR_ID_CARRERA", referencedColumnName = "ID_CARRERA")
    @ManyToOne(optional = false)
    private Carrera carIdCarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "malIdMalla")
    private List<Asignatura> asignaturaList;

    public Malla() {
    }

    public Malla(Integer idMalla) {
        this.idMalla = idMalla;
    }

    public Integer getIdMalla() {
        return idMalla;
    }

    public void setIdMalla(Integer idMalla) {
        this.idMalla = idMalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Carrera getCarIdCarrera() {
        return carIdCarrera;
    }

    public void setCarIdCarrera(Carrera carIdCarrera) {
        this.carIdCarrera = carIdCarrera;
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
        hash += (idMalla != null ? idMalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Malla)) {
            return false;
        }
        Malla other = (Malla) object;
        if ((this.idMalla == null && other.idMalla != null) || (this.idMalla != null && !this.idMalla.equals(other.idMalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
