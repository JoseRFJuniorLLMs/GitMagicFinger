/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Profesor;
import entity.ProfesoresPorCurso;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import sessionBeans.AlumnosPorCursoFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;
import sessionBeans.ProfesoresPorCursoFacadeLocal;

/**
 *
 * @author chevo
 */
@Named(value = "asistenciaCursoMB")
@RequestScoped
public class AsistenciaCursoMB {
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private ProfesoresPorCursoFacadeLocal profesoresPorCursoFacade;
    @EJB
    private AlumnosPorCursoFacadeLocal alumnosPorCursoFacade;
    
    private Profesor profesor;
    private List<ProfesoresPorCurso> ListaCursos;
    
    @PostConstruct
    public void init(){
        profesor = profesorFacade.find(6);
   //     alumnosPorCursoFacade.find(profesor.getIdProfesor());
    }
    
    
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
}
   
