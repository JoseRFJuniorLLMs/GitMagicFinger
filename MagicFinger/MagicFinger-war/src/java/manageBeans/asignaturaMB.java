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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.event.UnselectEvent;
import sessionBeans.CursoFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;

/**
 *
 * @author Nacho
 */
@Named(value = "asignaturaMB")
@RequestScoped
public class asignaturaMB {

    @EJB
    private CursoFacadeLocal cursoFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    private Profesor profesor;
    private List<Curso> ListCurso;
    private asignaturaDataModel ListaCursoData;
    private Curso cursoSeleccionado;

    @PostConstruct
    public void init() {
        profesor = profesorFacade.find(2);
        if (profesor != null) {
            ListCurso = profesor.getCursoList();
            ListaCursoData = new asignaturaDataModel(ListCurso);
        }
    }

    public ProfesorFacadeLocal getProfesorFacade() {
        return profesorFacade;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Curso Seleccionado", cursoSeleccionado.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", " Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void actualizarDatos() {
        FacesMessage msg = new FacesMessage("Los datos han sido actualizados", "datos: ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void cambiarTermino(SlideEndEvent event) {
        FacesMessage msg = new FacesMessage("Se ha cambiado el tiempo de termino", "Valor: " + event.getValue());
        //  cursoSeleccionado.setTermino((Integer)event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public asignaturaDataModel getListaCursoData() {
        return ListaCursoData;
    }

    public void setListaCursoData(asignaturaDataModel ListaCursoData) {
        this.ListaCursoData = ListaCursoData;
    }
}
