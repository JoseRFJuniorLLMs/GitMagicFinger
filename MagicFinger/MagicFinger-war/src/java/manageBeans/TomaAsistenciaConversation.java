/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.BloqueClase;
import entity.Curso;
import entity.Profesor;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;


/**
 *
 * @author chevo
 */
@Named(value = "tomaAsistenciaConversation")
@ConversationScoped
public class TomaAsistenciaConversation extends AbstractConversation implements Serializable {
    @Inject
    asistenciaCursoMB asistencia;
    
    private Profesor profesor;
    private Curso curso;
    private Date fecha;
    private List<BloqueClase> bloqueClaseList;
    
    public TomaAsistenciaConversation() {
        fecha = new Date();
        bloqueClaseList = null;
        System.out.println("se creo un session conversacional");
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<BloqueClase> getBloqueClaseList() {
        return bloqueClaseList;
    }

    public void setBloqueClaseList(List<BloqueClase> bloqueClaseList) {
        this.bloqueClaseList = bloqueClaseList;
    }
    
    
}
