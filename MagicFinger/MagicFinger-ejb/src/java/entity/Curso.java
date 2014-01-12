/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByUniversidad", query = "SELECT d FROM Curso d WHERE d.asignatura.malIdMalla.carIdCarrera.depIdDepartamento.facIdFacultad.uniIdUniversidad.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Curso.findByAsiIdAsignatura", query = "SELECT c FROM Curso c WHERE c.cursoPK.asiIdAsignatura = :asiIdAsignatura"),
    @NamedQuery(name = "Curso.findByTipIdTipoAsignatura", query = "SELECT c FROM Curso c WHERE c.cursoPK.tipIdTipoAsignatura = :tipIdTipoAsignatura"),
    @NamedQuery(name = "Curso.findBySemIdFecha", query = "SELECT c FROM Curso c WHERE c.cursoPK.semIdFecha = :semIdFecha"),
    @NamedQuery(name = "Curso.findByPorcentajeAprobacion", query = "SELECT c FROM Curso c WHERE c.porcentajeAprobacion = :porcentajeAprobacion"),
    @NamedQuery(name = "Curso.findByTermino", query = "SELECT c FROM Curso c WHERE c.termino = :termino")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CursoPK cursoPK;
    @Column(name = "PORCENTAJE_APROBACION")
    private Integer porcentajeAprobacion;
    @Column(name = "TERMINO")
    private Integer termino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<ProfesoresPorCurso> profesoresPorCursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<AlumnosDelCurso> alumnosDelCursoList;
    @JoinColumn(name = "ASI_ID_ASIGNATURA", referencedColumnName = "ID_ASIGNATURA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @JoinColumn(name = "SEM_ID_FECHA", referencedColumnName = "ID_FECHA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Semestre semestre;
    @JoinColumn(name = "TIP_ID_TIPO_ASIGNATURA", referencedColumnName = "ID_TIPO_ASIGNATURA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoAsignatura tipoAsignatura;
    @OneToMany(mappedBy = "curso")
    private List<BloqueClase> bloqueClaseList;
    @OneToMany(mappedBy = "curso")
    private List<Grupos> gruposList;

    public Curso() {
    }

    public Curso(CursoPK cursoPK) {
        this.cursoPK = cursoPK;
    }

    public Curso(int asiIdAsignatura, int tipIdTipoAsignatura, int semIdFecha) {
        this.cursoPK = new CursoPK(asiIdAsignatura, tipIdTipoAsignatura, semIdFecha);
    }

    public CursoPK getCursoPK() {
        return cursoPK;
    }

    public void setCursoPK(CursoPK cursoPK) {
        this.cursoPK = cursoPK;
    }

    public Integer getPorcentajeAprobacion() {
        return porcentajeAprobacion;
    }

    public void setPorcentajeAprobacion(Integer porcentajeAprobacion) {
        this.porcentajeAprobacion = porcentajeAprobacion;
    }

    public Integer getTermino() {
        return termino;
    }

    public void setTermino(Integer termino) {
        this.termino = termino;
    }

    @XmlTransient
    public List<ProfesoresPorCurso> getProfesoresPorCursoList() {
        return profesoresPorCursoList;
    }

    public void setProfesoresPorCursoList(List<ProfesoresPorCurso> profesoresPorCursoList) {
        this.profesoresPorCursoList = profesoresPorCursoList;
    }

    @XmlTransient
    public List<AlumnosDelCurso> getAlumnosDelCursoList() {
        return alumnosDelCursoList;
    }

    public void setAlumnosDelCursoList(List<AlumnosDelCurso> alumnosDelCursoList) {
        this.alumnosDelCursoList = alumnosDelCursoList;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public TipoAsignatura getTipoAsignatura() {
        return tipoAsignatura;
    }

    public void setTipoAsignatura(TipoAsignatura tipoAsignatura) {
        this.tipoAsignatura = tipoAsignatura;
    }

    @XmlTransient
    public List<BloqueClase> getBloqueClaseList() {
        return bloqueClaseList;
    }

    public void setBloqueClaseList(List<BloqueClase> bloqueClaseList) {
        this.bloqueClaseList = bloqueClaseList;
    }

    @XmlTransient
    public List<Grupos> getGruposList() {
        return gruposList;
    }

    public void setGruposList(List<Grupos> gruposList) {
        this.gruposList = gruposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoPK != null ? cursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.cursoPK == null && other.cursoPK != null) || (this.cursoPK != null && !this.cursoPK.equals(other.cursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getAsignatura().getNombre() + "-" + getTipoAsignatura().getNombre();
    }
    
}
