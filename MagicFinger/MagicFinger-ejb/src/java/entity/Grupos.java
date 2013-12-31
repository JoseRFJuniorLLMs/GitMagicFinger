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
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g"),
    @NamedQuery(name = "Grupos.findByCursoId2", query = "SELECT g FROM Grupos g WHERE g.gruposPK.cursoId2 = :cursoId2"),
    @NamedQuery(name = "Grupos.findByCursoId", query = "SELECT g FROM Grupos g WHERE g.gruposPK.cursoId = :cursoId"),
    @NamedQuery(name = "Grupos.findByAlumnosDelCursoId2", query = "SELECT g FROM Grupos g WHERE g.gruposPK.alumnosDelCursoId2 = :alumnosDelCursoId2"),
    @NamedQuery(name = "Grupos.findByAlumnosDelCursoId3", query = "SELECT g FROM Grupos g WHERE g.gruposPK.alumnosDelCursoId3 = :alumnosDelCursoId3"),
    @NamedQuery(name = "Grupos.findByAlumnosDelCursoId", query = "SELECT g FROM Grupos g WHERE g.gruposPK.alumnosDelCursoId = :alumnosDelCursoId")})
public class Grupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruposPK gruposPK;
    @Lob
    @Size(max = 65535)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumns({
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID2", referencedColumnName = "CURSO_ID2", insertable = false, updatable = false),
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID3", referencedColumnName = "CURSO_ID", insertable = false, updatable = false),
        @JoinColumn(name = "ALUMNOS_DEL_CURSO_ID", referencedColumnName = "ALUMNO_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private AlumnosDelCurso alumnosDelCurso;
    @JoinColumns({
        @JoinColumn(name = "CURSO_ID2", referencedColumnName = "ASIGNATURA_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CURSO_ID", referencedColumnName = "TIPO_ASIGNATURA_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Curso curso;

    public Grupos() {
    }

    public Grupos(GruposPK gruposPK) {
        this.gruposPK = gruposPK;
    }

    public Grupos(int cursoId2, int cursoId, int alumnosDelCursoId2, int alumnosDelCursoId3, int alumnosDelCursoId) {
        this.gruposPK = new GruposPK(cursoId2, cursoId, alumnosDelCursoId2, alumnosDelCursoId3, alumnosDelCursoId);
    }

    public GruposPK getGruposPK() {
        return gruposPK;
    }

    public void setGruposPK(GruposPK gruposPK) {
        this.gruposPK = gruposPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AlumnosDelCurso getAlumnosDelCurso() {
        return alumnosDelCurso;
    }

    public void setAlumnosDelCurso(AlumnosDelCurso alumnosDelCurso) {
        this.alumnosDelCurso = alumnosDelCurso;
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
        hash += (gruposPK != null ? gruposPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.gruposPK == null && other.gruposPK != null) || (this.gruposPK != null && !this.gruposPK.equals(other.gruposPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Grupos[ gruposPK=" + gruposPK + " ]";
    }
    
}
