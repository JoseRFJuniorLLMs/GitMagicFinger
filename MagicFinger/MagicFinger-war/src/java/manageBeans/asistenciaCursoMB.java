package manageBeans;

import entity.Profesor;
import entity.ProfesoresPorCurso;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.ProfesorFacadeLocal;
import sessionBeans.asignaturas.ProfesorPorCursoLocal;


/**
 *
 * @author chevo
 */
@Named(value = "asistenciaCursoMB")
@RequestScoped
public class asistenciaCursoMB {   
    @EJB
    private ProfesorPorCursoLocal profesorPorCurso;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    
    private List<ProfesoresPorCurso> ListCurso;
    private Profesor profesor;
    
    @PostConstruct
    public void init(){
        profesor = profesorFacade.find(1);
        ListCurso = profesorPorCurso.findByProfesor(profesor.getIdProfesor());
        System.out.println("tamaño_______" + ListCurso.size());
        System.out.println("asdfasd-____" + ListCurso.toString());
    }

    public List<ProfesoresPorCurso> getListCurso() {
        return ListCurso;
    }

    public void setListCurso(List<ProfesoresPorCurso> ListCurso) {
        this.ListCurso = ListCurso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}