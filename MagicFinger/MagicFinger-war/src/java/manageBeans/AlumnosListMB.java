package manageBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Alumno;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.AlumnoFacadeLocal;

/**
 *
 * @author chevo
 */
@Named(value = "alumnosListMB")
@RequestScoped
public class AlumnosListMB {
    @EJB
    private AlumnoFacadeLocal alumnoFacade;
    
    private List<Alumno> ListaAlumnos;
    
    public AlumnosListMB() {
        
    }
    
    @PostConstruct
    public void init(){
        ListaAlumnos = alumnoFacade.findAll();
    }

    public List<Alumno> getListaAlumnos() {
        return ListaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> ListaAlumnos) {
        this.ListaAlumnos = ListaAlumnos;
    }
    
}
