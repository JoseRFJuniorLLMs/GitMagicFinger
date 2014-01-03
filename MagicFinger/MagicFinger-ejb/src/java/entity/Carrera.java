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
@Table(name = "carrera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.findByIdCarr", query = "SELECT c FROM Carrera c WHERE c.idCarr = :idCarr")})
public class Carrera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARR")
    private Integer idCarr;
    @Lob
    @Size(max = 65535)
    @Column(name = "NOMBRE_CARRERA")
    private String nombreCarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreraId")
    private List<Malla> mallaList;
    @JoinColumn(name = "DEPARTAMENTO_ID", referencedColumnName = "ID_DEPARTAMENTO")
    @ManyToOne(optional = false)
    private Departamento departamentoId;

    public Carrera() {
    }

    public Carrera(Integer idCarr) {
        this.idCarr = idCarr;
    }

    public Integer getIdCarr() {
        return idCarr;
    }

    public void setIdCarr(Integer idCarr) {
        this.idCarr = idCarr;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    @XmlTransient
    public List<Malla> getMallaList() {
        return mallaList;
    }

    public void setMallaList(List<Malla> mallaList) {
        this.mallaList = mallaList;
    }

    public Departamento getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Departamento departamentoId) {
        this.departamentoId = departamentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarr != null ? idCarr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.idCarr == null && other.idCarr != null) || (this.idCarr != null && !this.idCarr.equals(other.idCarr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreCarrera;
    }
    
}
