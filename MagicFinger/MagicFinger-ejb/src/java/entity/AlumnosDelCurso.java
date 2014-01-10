/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "alumnos_del_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlumnosDelCurso.findAll", query = "SELECT a FROM AlumnosDelCurso a"),
    @NamedQuery(name = "AlumnoDelCurso.findByUniversidad", query = "SELECT d FROM AlumnosDelCurso d WHERE d.alumno.uniIdUniversidad.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "AlumnosDelCurso.findByCurAsiIdAsignatura", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.curAsiIdAsignatura = :curAsiIdAsignatura"),
    @NamedQuery(name = "AlumnosDelCurso.findByCurTipIdTipoAsignatura", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.curTipIdTipoAsignatura = :curTipIdTipoAsignatura"),
    @NamedQuery(name = "AlumnosDelCurso.findByCurSemIdFecha", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.curSemIdFecha = :curSemIdFecha"),
    @NamedQuery(name = "AlumnosDelCurso.findByAluIdAlumno", query = "SELECT a FROM AlumnosDelCurso a WHERE a.alumnosDelCursoPK.aluIdAlumno = :aluIdAlumno")})
public class AlumnosDelCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumnosDelCursoPK alumnosDelCursoPK;
    @JoinColumn(name = "ALU_ID_ALUMNO", referencedColumnName = "ID_ALUMNO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumns({
        @JoinColumn(name = "CUR_ASI_ID_ASIGNATURA", referencedColumnName = "ASI_ID_ASIGNATURA", insertable = false, updatable = false),
        @JoinColumn(name = "CUR_TIP_ID_TIPO_ASIGNATURA", referencedColumnName = "TIP_ID_TIPO_ASIGNATURA", insertable = false, updatable = false),
        @JoinColumn(name = "CUR_SEM_ID_FECHA", referencedColumnName = "SEM_ID_FECHA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "GRU_ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne
    private Grupos gruIdGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumnosDelCurso")
    private List<Asistencia> asistenciaList;

    public AlumnosDelCurso() {
    }

    public AlumnosDelCurso(AlumnosDelCursoPK alumnosDelCursoPK) {
        this.alumnosDelCursoPK = alumnosDelCursoPK;
    }

    public AlumnosDelCurso(int curAsiIdAsignatura, int curTipIdTipoAsignatura, int curSemIdFecha, int aluIdAlumno) {
        this.alumnosDelCursoPK = new AlumnosDelCursoPK(curAsiIdAsignatura, curTipIdTipoAsignatura, curSemIdFecha, aluIdAlumno);
    }

    public AlumnosDelCursoPK getAlumnosDelCursoPK() {
        return alumnosDelCursoPK;
    }

    public void setAlumnosDelCursoPK(AlumnosDelCursoPK alumnosDelCursoPK) {
        this.alumnosDelCursoPK = alumnosDelCursoPK;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Grupos getGruIdGrupo() {
        return gruIdGrupo;
    }

    public void setGruIdGrupo(Grupos gruIdGrupo) {
        this.gruIdGrupo = gruIdGrupo;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnosDelCursoPK != null ? alumnosDelCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnosDelCurso)) {
            return false;
        }
        AlumnosDelCurso other = (AlumnosDelCurso) object;
        if ((this.alumnosDelCursoPK == null && other.alumnosDelCursoPK != null) || (this.alumnosDelCursoPK != null && !this.alumnosDelCursoPK.equals(other.alumnosDelCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AlumnosDelCurso[ alumnosDelCursoPK=" + alumnosDelCursoPK + " ]";
    }
    
}
