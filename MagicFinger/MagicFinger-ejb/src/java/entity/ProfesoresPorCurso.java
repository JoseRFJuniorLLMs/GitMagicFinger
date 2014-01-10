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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chevo
 */
@Entity
@Table(name = "profesores_por_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesoresPorCurso.findAll", query = "SELECT p FROM ProfesoresPorCurso p"),
    @NamedQuery(name = "ProfesoresPorCurso.findByUniversidad", query = "SELECT d FROM ProfesoresPorCurso d"),
    @NamedQuery(name = "ProfesoresPorCurso.findByCurAsiIdAsignatura", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.curAsiIdAsignatura = :curAsiIdAsignatura"),
    @NamedQuery(name = "ProfesoresPorCurso.findByCurTipIdTipoAsignatura", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.curTipIdTipoAsignatura = :curTipIdTipoAsignatura"),
    @NamedQuery(name = "ProfesoresPorCurso.findByCurSemIdFecha", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.curSemIdFecha = :curSemIdFecha"),
    @NamedQuery(name = "ProfesoresPorCurso.findByProIdProfesor", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.profesoresPorCursoPK.proIdProfesor = :proIdProfesor"),
    @NamedQuery(name = "ProfesoresPorCurso.findByIdProCur", query = "SELECT p FROM ProfesoresPorCurso p WHERE p.idProCur = :idProCur")})
public class ProfesoresPorCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProfesoresPorCursoPK profesoresPorCursoPK;
    @Column(name = "ID_PRO_CUR")
    private Integer idProCur;
    @JoinColumns({
        @JoinColumn(name = "CUR_ASI_ID_ASIGNATURA", referencedColumnName = "ASI_ID_ASIGNATURA", insertable = false, updatable = false),
        @JoinColumn(name = "CUR_TIP_ID_TIPO_ASIGNATURA", referencedColumnName = "TIP_ID_TIPO_ASIGNATURA", insertable = false, updatable = false),
        @JoinColumn(name = "CUR_SEM_ID_FECHA", referencedColumnName = "SEM_ID_FECHA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "PRO_ID_PROFESOR", referencedColumnName = "ID_PROFESOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profesor profesor;

    public ProfesoresPorCurso() {
    }

    public ProfesoresPorCurso(ProfesoresPorCursoPK profesoresPorCursoPK) {
        this.profesoresPorCursoPK = profesoresPorCursoPK;
    }

    public ProfesoresPorCurso(int curAsiIdAsignatura, int curTipIdTipoAsignatura, int curSemIdFecha, int proIdProfesor) {
        this.profesoresPorCursoPK = new ProfesoresPorCursoPK(curAsiIdAsignatura, curTipIdTipoAsignatura, curSemIdFecha, proIdProfesor);
    }

    public ProfesoresPorCursoPK getProfesoresPorCursoPK() {
        return profesoresPorCursoPK;
    }

    public void setProfesoresPorCursoPK(ProfesoresPorCursoPK profesoresPorCursoPK) {
        this.profesoresPorCursoPK = profesoresPorCursoPK;
    }

    public Integer getIdProCur() {
        return idProCur;
    }

    public void setIdProCur(Integer idProCur) {
        this.idProCur = idProCur;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profesoresPorCursoPK != null ? profesoresPorCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesoresPorCurso)) {
            return false;
        }
        ProfesoresPorCurso other = (ProfesoresPorCurso) object;
        if ((this.profesoresPorCursoPK == null && other.profesoresPorCursoPK != null) || (this.profesoresPorCursoPK != null && !this.profesoresPorCursoPK.equals(other.profesoresPorCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProfesoresPorCurso[ profesoresPorCursoPK=" + profesoresPorCursoPK + " ]";
    }
    
}
