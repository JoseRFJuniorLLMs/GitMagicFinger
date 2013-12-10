package manageBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Curso;
import entity.Profesor;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import sessionBeans.ProfesorFacadeLocal;

/**
 *
 * @author Nacho
 */
@Named(value = "asignaturaMB")
@RequestScoped
public class asignaturaMB {
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    private Profesor profesor;
    private List<Curso> ListCurso;
    private Curso cursoSeleccionado;
    
     @PostConstruct
     public void init(){
     profesor = profesorFacade.find(2);
        if(profesor!=null){
            ListCurso = profesor.getCursoList();
        }
     }

    public ProfesorFacadeLocal getProfesorFacade() {
        return profesorFacade;
    }

    public void setProfesorFacade(ProfesorFacadeLocal profesorFacade) {
        this.profesorFacade = profesorFacade;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Curso> getListCurso() {
        return ListCurso;
    }

    public void setListCurso(List<Curso> ListCurso) {
        this.ListCurso = ListCurso;
    }

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(Curso cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }
}
