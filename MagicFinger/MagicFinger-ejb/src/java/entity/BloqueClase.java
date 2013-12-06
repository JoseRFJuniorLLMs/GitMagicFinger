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
@Table(name = "bloque_clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BloqueClase.findAll", query = "SELECT b FROM BloqueClase b"),
    @NamedQuery(name = "BloqueClase.findByIdBloque", query = "SELECT b FROM BloqueClase b WHERE b.idBloque = :idBloque"),
    @NamedQuery(name = "BloqueClase.findByBloque", query = "SELECT b FROM BloqueClase b WHERE b.bloque = :bloque")})
public class BloqueClase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BLOQUE")
    private Integer idBloque;
    @Column(name = "BLOQUE")
    private Integer bloque;
    @Lob
    @Size(max = 65535)
    @Column(name = "DIA_SEMANA")
    private String diaSemana;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bloqueClaseId")
    private List<Asistencia> asistenciaList;
    @JoinColumns({
        @JoinColumn(name = "CURSO_ID2", referencedColumnName = "ASIGNATURA_ID"),
        @JoinColumn(name = "CURSO_ID", referencedColumnName = "TIPO_ASIGNATURA_ID")})
    @ManyToOne
    private Curso curso;

    public BloqueClase() {
    }

    public BloqueClase(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
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
        hash += (idBloque != null ? idBloque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueClase)) {
            return false;
        }
        BloqueClase other = (BloqueClase) object;
        if ((this.idBloque == null && other.idBloque != null) || (this.idBloque != null && !this.idBloque.equals(other.idBloque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BloqueClase[ idBloque=" + idBloque + " ]";
    }
    
}
