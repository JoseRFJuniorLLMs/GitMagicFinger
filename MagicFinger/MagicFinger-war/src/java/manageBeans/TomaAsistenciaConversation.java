/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Curso;
import entity.Profesor;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author chevo
 */
@Named(value = "tomaAsistenciaConversation")
@ConversationScoped
public class TomaAsistenciaConversation extends AbstractConversation implements Serializable {
    private Profesor profesor;
    private Curso curso;
    
    public TomaAsistenciaConversation() {
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

    
}
