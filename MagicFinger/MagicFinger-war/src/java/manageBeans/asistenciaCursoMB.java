package manageBeans;

import entity.AlumnosDelCurso;
import entity.BloqueClase;
import entity.Curso;
import entity.Profesor;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.AsistenciaFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;
import sessionBeans.asignaturas.asistenciaSBLocal;


/**
 *
 * @author chevo
 */
@Named(value = "asistenciaCursoMB")
@RequestScoped
public class asistenciaCursoMB {   
    @EJB
    private asistenciaSBLocal asistenciaSB;
    @EJB
    private AsistenciaFacadeLocal asistenciaFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    
    
    private Profesor profesor;
    private List<Curso> ListCurso;
    private Curso curso;
    
    private BloqueClase bloqueClase;
    private int valor;
    
    private AlumnosDelCurso alumnoPorClase;
    
    @PostConstruct
    public void init(){
        profesor = profesorFacade.find(1);
        if(profesor!=null){
            ListCurso = profesor.getCursoList();
            if(!ListCurso.isEmpty()){
                curso = ListCurso.get(0);
                alumnoPorClase = curso.getAlumnosDelCursoList().get(0);
                bloqueClase = curso.getBloqueClaseList().get(0);
                Date fecha = new Date();
                valor = asistenciaSB.alumnoAsiste( alumnoPorClase , bloqueClase, fecha);
            }
        }
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public BloqueClase getBloqueClase() {
        return bloqueClase;
    }

    public void setBloqueClase(BloqueClase bloqueClase) {
        this.bloqueClase = bloqueClase;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public AlumnosDelCurso getAlumnoPorClase() {
        return alumnoPorClase;
    }

    public void setAlumnoPorClase(AlumnosDelCurso alumnoPorClase) {
        this.alumnoPorClase = alumnoPorClase;
    }
    
    
}